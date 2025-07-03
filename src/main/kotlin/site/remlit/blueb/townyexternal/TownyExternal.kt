package site.remlit.blueb.townyexternal

import org.bukkit.plugin.java.JavaPlugin
import kotlin.concurrent.thread

class TownyExternal : JavaPlugin() {
    override fun onEnable() {
        instance = this

        config.addDefault("http-port", 8064)
        config.options().copyDefaults(true)
        saveConfig()

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
