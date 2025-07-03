package site.remlit.blueb.townyexternal

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

        /*
        * todo:
        * config cache
        * config cache reset events
        * config cache storage type (redis, h2, postgres)
        *
        * respect config for host & add auth
        *
        * register event listener
        *
        * bstats metrics on cache type and require auth type
        * */

        EventListener.register()

        httpServer = thread(name = "TownyExternal") {
            main(instance.config.get("http-port")?.toString()?.toInt() ?: 8064)
        }
        httpServerInitialized = true
    }

    companion object {
        lateinit var instance: JavaPlugin

        var httpServerInitialized = false
        lateinit var httpServer: Thread
    }
}
