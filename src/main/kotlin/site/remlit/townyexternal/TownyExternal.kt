package site.remlit.townyexternal

import co.aikar.commands.BukkitCommandManager
import com.palmergames.bukkit.towny.TownyAPI
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie
import org.bstats.charts.SingleLineChart
import org.bukkit.plugin.java.JavaPlugin
import site.remlit.townyexternal.service.CacheService
import kotlin.concurrent.thread

class TownyExternal : JavaPlugin() {
    override fun onEnable() {
        instance = this
        commandManager = BukkitCommandManager(this)

        config.addDefault("http-port", 8064)
        config.addDefault("http-address", "127.0.0.1")
        config.addDefault("require-credential", false)
        config.addDefault("cache", "h2")
        config.options().copyDefaults(true)
        saveConfig()

        val towny = TownyAPI.getInstance()

        val metrics = Metrics(this, 26368)
        metrics.addCustomChart(SimplePie("require_credential") { config.getString("require-credential", "false") })
        metrics.addCustomChart(SimplePie("cache_type") { config.getString("cache", "h2") })
        metrics.addCustomChart(SingleLineChart("towns_on_servers_using_townyexternal") { towny.towns.size })
        metrics.addCustomChart(SingleLineChart("nations_on_servers_using_townyexternal") { towny.nations.size })
        metrics.addCustomChart(SingleLineChart("residents_on_servers_using_townyexternal") { towny.residents.size })

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
        Commands.register()

        CacheService.init()

        httpServer = thread(name = "TownyExternal") { main() }
        httpServerInitialized = true
    }

    companion object {
        lateinit var instance: JavaPlugin
        lateinit var commandManager: BukkitCommandManager

        var httpServerInitialized = false
        lateinit var httpServer: Thread
    }
}
