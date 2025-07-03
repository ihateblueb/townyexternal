package site.remlit.blueb.townyexternal

import com.palmergames.bukkit.towny.event.NewTownEvent
import com.palmergames.bukkit.towny.event.town.TownMergeEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class EventListener : Listener {
    /*
    on new day: reset & repopulate cache
    on event trigger: reset cache
    --
    go through all nation, town, resident, player events
    * */
    @EventHandler
    fun onTownCreate(event: NewTownEvent) {}

    fun resetTownCache() {}
    fun resetNationCache() {}
    fun resetResidentCache() {}

    companion object {
        fun register() = TownyExternal.instance.server.pluginManager.registerEvents(EventListener(), TownyExternal.instance)
    }
}