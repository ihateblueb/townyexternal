package site.remlit.blueb.townyexternal

import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin
import kotlin.concurrent.thread

class TownyExternal : JavaPlugin() {
    override fun onEnable() {
        instance = this

        config.addDefault("http-port", 8064)
        config.addDefault("http-address", "127.0.0.1")
        config.addDefault("require-credential", false)
        config.addDefault("cache", "h2")
        config.options().copyDefaults(true)
        saveConfig()

        Metrics(this, 26368)

        /*
        * todo:
        * config cache
        * config cache reset events
        * config cache storage type (redis, h2, postgres)
        *
        * add auth
        *
        * register event listener
        *
        * bstats metrics on cache type and require auth type
        * */

        EventListener.register()

        httpServer = thread(name = "TownyExternal") { main() }
        httpServerInitialized = true
    }

    companion object {
        lateinit var instance: JavaPlugin

        var httpServerInitialized = false
        lateinit var httpServer: Thread
    }
}
