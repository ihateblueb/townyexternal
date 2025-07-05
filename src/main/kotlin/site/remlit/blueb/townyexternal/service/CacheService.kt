package site.remlit.blueb.townyexternal.service

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import redis.clients.jedis.JedisPool
import site.remlit.blueb.townyexternal.TownyExternal
import site.remlit.blueb.townyexternal.model.CacheMode
import java.sql.Connection
import java.sql.SQLException
import kotlin.concurrent.thread

class CacheService {
    companion object {
        private var mode: CacheMode = when (TownyExternal.instance.config.get("cache")?.toString() ?: "h2") {
            "h2" -> CacheMode.H2
            "redis" -> CacheMode.REDIS
            "postgres" -> CacheMode.POSTGRES
            else -> CacheMode.H2
        }

        private var ready = false

        private lateinit var jedisPool: JedisPool
        private lateinit var hikariConfig: HikariConfig
        private lateinit var hikariConnection: Connection

        private var redisPrefix = TownyExternal.instance.config.get("redis-prefix")?.toString() ?: "townyexternal:"

        fun init() {
            when (mode) {
                CacheMode.H2 -> {
                    Class.forName("org.h2.Driver")

                    hikariConfig = HikariConfig()
                    hikariConfig.jdbcUrl = "jdbc:h2:mem:townyexternal"

                    val dataSource = HikariDataSource(hikariConfig)
                    try {
                        hikariConnection = dataSource.connection
                    } catch (e: SQLException) { throw Exception("Could not initialize cache database connection: ${e.message}") }

                    runSqlMigrations()

                    ready = true
                }
                CacheMode.REDIS -> {
                    val host = TownyExternal.instance.config.get("redis-host")?.toString() ?: "127.0.0.1"
                    val port = TownyExternal.instance.config.get("redis-port")?.toString()?.toInt() ?: 6379

                    jedisPool = try {
                        JedisPool(host, port)
                    } catch (e: Exception) { throw Exception("Could not initialize cache database connection: ${e.message}") }

                    ready = true
                }
                CacheMode.POSTGRES -> {
                    Class.forName("org.postgres.Driver")

                    val host = TownyExternal.instance.config.get("postgres-host")?.toString() ?: "127.0.0.1"
                    val port = TownyExternal.instance.config.get("postgres-port")?.toString() ?: "5432"
                    val db = TownyExternal.instance.config.get("postgres-db")?.toString() ?: "townyexternal"
                    val user = TownyExternal.instance.config.get("postgres-user")?.toString()
                    val pass = TownyExternal.instance.config.get("postgres-pass")?.toString()

                    hikariConfig = HikariConfig()
                    hikariConfig.jdbcUrl = "jdbc:postgresql://$host:$port/$db"
                    hikariConfig.username = user
                    hikariConfig.password = pass

                    val dataSource = HikariDataSource(hikariConfig)
                    try {
                        hikariConnection = dataSource.connection
                    } catch (e: SQLException) { throw Exception("Could not initialize cache database connection: ${e.message}") }

                    runSqlMigrations()

                    ready = true
                }
            }
            TownyExternal.instance.logger.info("Initialized cache database")
        }

        private fun runSqlMigrations() {
            hikariConnection.prepareStatement("CREATE TABLE IF NOT EXISTS CACHE (a text, b text)").use { it.execute() }
        }

        fun get(key: String): String? {
            if (!ready)
                throw Exception("Cache not ready")

            return when (mode) {
                CacheMode.H2, CacheMode.POSTGRES -> {
                    hikariConnection.createStatement().use { statement ->
                        statement.executeQuery("SELECT * FROM CACHE WHERE a = '$key'").use { rs ->
                            if (rs.next())
                                rs.getString("b")
                            else null
                        }
                    }
                }
                CacheMode.REDIS -> {
                    jedisPool.resource.use { jedis ->
                        jedis.get("$redisPrefix$key")
                    }
                }
            }
        }

        fun set(key: String, value: String) {
            if (!ready)
                throw Exception("Cache not ready")

            thread(name = "TownyExternalCacheSetter") {
                when (mode) {
                    CacheMode.H2, CacheMode.POSTGRES -> {
                        hikariConnection.prepareStatement("INSERT INTO CACHE (a, b) VALUES (?, ?)").use {
                            it.setString(1, key.toString())
                            it.setString(2, value)
                            it.execute()
                        }
                    }
                    CacheMode.REDIS -> {
                        jedisPool.resource.use { jedis ->
                            jedis.set("$redisPrefix$key", value)
                        }
                    }
                }
            }
        }

        fun clear(key: String) {
            if (!ready)
                throw Exception("Cache not ready")

            thread(name = "TownyExternalCacheClearer") {
                when (mode) {
                    CacheMode.H2, CacheMode.POSTGRES -> {
                        hikariConnection.prepareStatement("DELETE FROM CACHE WHERE a = ?").use {
                            it.setString(1, key)
                            it.execute()
                        }
                    }
                    CacheMode.REDIS -> {
                        jedisPool.resource.use { jedis ->
                            jedis.del("$redisPrefix$key")
                        }
                    }
                }
            }
        }
    }
}