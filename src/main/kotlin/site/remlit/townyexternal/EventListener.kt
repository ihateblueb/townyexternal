package site.remlit.townyexternal

import com.palmergames.bukkit.towny.event.DeleteNationEvent
import com.palmergames.bukkit.towny.event.DeleteTownEvent
import com.palmergames.bukkit.towny.event.NationAcceptAllyRequestEvent
import com.palmergames.bukkit.towny.event.NationAddEnemyEvent
import com.palmergames.bukkit.towny.event.NationDenyAllyRequestEvent
import com.palmergames.bukkit.towny.event.NationRemoveAllyEvent
import com.palmergames.bukkit.towny.event.NationRemoveEnemyEvent
import com.palmergames.bukkit.towny.event.NationRemoveTownEvent
import com.palmergames.bukkit.towny.event.NewDayEvent
import com.palmergames.bukkit.towny.event.NewNationEvent
import com.palmergames.bukkit.towny.event.NewTownEvent
import com.palmergames.bukkit.towny.event.RenameNationEvent
import com.palmergames.bukkit.towny.event.RenameTownEvent
import com.palmergames.bukkit.towny.event.TownAddResidentEvent
import com.palmergames.bukkit.towny.event.TownClaimEvent
import com.palmergames.bukkit.towny.event.TownRemoveResidentEvent
import com.palmergames.bukkit.towny.event.nation.NationKingChangeEvent
import com.palmergames.bukkit.towny.event.nation.NationMergeEvent
import com.palmergames.bukkit.towny.event.nation.NationSanctionTownAddEvent
import com.palmergames.bukkit.towny.event.nation.NationSanctionTownRemoveEvent
import com.palmergames.bukkit.towny.event.nation.NationTownLeaveEvent
import com.palmergames.bukkit.towny.event.nation.toggle.NationToggleEvent
import com.palmergames.bukkit.towny.event.town.TownAddAlliedTownEvent
import com.palmergames.bukkit.towny.event.town.TownAddEnemiedTownEvent
import com.palmergames.bukkit.towny.event.town.TownConqueredEvent
import com.palmergames.bukkit.towny.event.town.TownMayorChangeEvent
import com.palmergames.bukkit.towny.event.town.TownMergeEvent
import com.palmergames.bukkit.towny.event.town.TownOutlawAddEvent
import com.palmergames.bukkit.towny.event.town.TownOutlawRemoveEvent
import com.palmergames.bukkit.towny.event.town.TownReclaimedEvent
import com.palmergames.bukkit.towny.event.town.TownRemoveAlliedTownEvent
import com.palmergames.bukkit.towny.event.town.TownRemoveEnemiedTownEvent
import com.palmergames.bukkit.towny.event.town.TownRuinedEvent
import com.palmergames.bukkit.towny.event.town.TownSetSpawnEvent
import com.palmergames.bukkit.towny.event.town.TownTrustAddEvent
import com.palmergames.bukkit.towny.event.town.TownTrustRemoveEvent
import com.palmergames.bukkit.towny.event.town.TownTrustTownAddEvent
import com.palmergames.bukkit.towny.event.town.TownTrustTownRemoveEvent
import com.palmergames.bukkit.towny.event.town.TownUnclaimEvent
import com.palmergames.bukkit.towny.event.town.TownUnconquerEvent
import com.palmergames.bukkit.towny.event.town.toggle.TownToggleEvent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import site.remlit.townyexternal.service.CacheService
import site.remlit.townyexternal.service.NationService
import site.remlit.townyexternal.service.TownService
import kotlin.concurrent.thread

@Suppress("unused")
class EventListener : Listener {
    @EventHandler
    fun onNewDay(event: NewDayEvent) { resetAllCaches() }

    @EventHandler
    fun onNewTown(event: NewTownEvent) { resetTownCache() }
    @EventHandler
    fun onDeleteTown(event: DeleteTownEvent) { resetTownCache() }
    @EventHandler
    fun onRenameTown(event: RenameTownEvent) { resetTownCache() }
    @EventHandler
    fun onTownToggle(event: TownToggleEvent) { resetTownCache() }
    @EventHandler
    fun onTownAddAlliedTown(event: TownAddAlliedTownEvent) { resetTownCache() }
    @EventHandler
    fun onTownAddEnemiedTown(event: TownAddEnemiedTownEvent) { resetTownCache() }
    @EventHandler
    fun onTownConquered(event: TownConqueredEvent) { resetTownCache() }
    @EventHandler
    fun onTownMayorChange(event: TownMayorChangeEvent) { resetTownCache() }
    @EventHandler
    fun onTownMerge(event: TownMergeEvent) { resetTownCache() }
    @EventHandler
    fun onTownOutlawAdd(event: TownOutlawAddEvent) { resetTownCache() }
    @EventHandler
    fun onTownOutlawRemove(event: TownOutlawRemoveEvent) { resetTownCache() }
    @EventHandler
    fun onTownReclaimed(event: TownReclaimedEvent) { resetTownCache() }
    @EventHandler
    fun onTownRemoveAlliedTown(event: TownRemoveAlliedTownEvent) { resetTownCache() }
    @EventHandler
    fun onTownRemoveEnemiedTown(event: TownRemoveEnemiedTownEvent) { resetTownCache() }
    @EventHandler
    fun onTownRuined(event: TownRuinedEvent) { resetTownCache() }
    @EventHandler
    fun onTownSetSpawn(event: TownSetSpawnEvent) { resetTownCache() }
    @EventHandler
    fun onTownTrustAdd(event: TownTrustAddEvent) { resetTownCache() }
    @EventHandler
    fun onTownTrustRemove(event: TownTrustRemoveEvent) { resetTownCache() }
    @EventHandler
    fun onTownTrustTownAdd(event: TownTrustTownAddEvent) { resetTownCache() }
    @EventHandler
    fun onTownTrustTownRemove(event: TownTrustTownRemoveEvent) { resetTownCache() }
    @EventHandler
    fun onTownUnclaim(event: TownUnclaimEvent) { resetTownCache() }
    @EventHandler
    fun onTownUnconquer(event: TownUnconquerEvent) { resetTownCache() }
    @EventHandler
    fun onTownAddResident(event: TownAddResidentEvent) { resetTownCache() }
    @EventHandler
    fun onTownRemoveResident(event: TownRemoveResidentEvent) { resetTownCache() }
    @EventHandler
    fun onTownClaim(event: TownClaimEvent) { resetTownCache() }

    @EventHandler
    fun onNewNation(event: NewNationEvent) { resetNationCache(); resetTownCache() }
    @EventHandler
    fun onDeleteNation(event: DeleteNationEvent) { resetNationCache(); resetTownCache() }
    @EventHandler
    fun onRenameNation(event: RenameNationEvent) { resetNationCache() }
    @EventHandler
    fun onNationToggle(event: NationToggleEvent) { resetNationCache() }
    @EventHandler
    fun onNationKingChange(event: NationKingChangeEvent) { resetNationCache() }
    @EventHandler
    fun onNationMerge(event: NationMergeEvent) { resetNationCache(); resetTownCache() }
    @EventHandler
    fun onNationSanctionTownAdd(event: NationSanctionTownAddEvent) { resetNationCache(); resetTownCache() }
    @EventHandler
    fun onNationSanctionTownRemove(event: NationSanctionTownRemoveEvent) { resetNationCache(); resetTownCache() }
    @EventHandler
    fun onNationTownLeave(event: NationTownLeaveEvent) { resetNationCache(); resetTownCache() }
    @EventHandler
    fun onNationAcceptAllyRequest(event: NationAcceptAllyRequestEvent) { resetNationCache() }
    @EventHandler
    fun onNationDenyAllyRequest(event: NationDenyAllyRequestEvent) { resetNationCache() }
    @EventHandler
    fun onNationAddEnemy(event: NationAddEnemyEvent) { resetNationCache() }
    @EventHandler
    fun onNationRemoveEnemy(event: NationRemoveEnemyEvent) { resetNationCache() }
    @EventHandler
    fun onNationRemoveAlly(event: NationRemoveAllyEvent) { resetNationCache() }
    @EventHandler
    fun onNationRemoveTown(event: NationRemoveTownEvent) { resetNationCache(); resetTownCache() }


    val cacheTaskScope = CoroutineScope(Dispatchers.Default + CoroutineName("TownyExternal-CacheTask"))

    fun resetAllCaches() {
        resetTownCache()
        resetNationCache()
        resetResidentCache()
    }

    fun resetTownCache() {
        cacheTaskScope.launch {
            val towns = TownService.getTowns()
            for (town in towns) {
                CacheService.clear("town_${town.uuid}")
            }
        }
    }

    fun resetNationCache() {
        cacheTaskScope.launch {
            val nations = NationService.getNations()
            for (nation in nations) {
                CacheService.clear("nation_${nation.uuid}")
            }
        }
    }

    fun resetResidentCache() {}

    companion object {
        fun register() = TownyExternal.instance.server.pluginManager.registerEvents(EventListener(), TownyExternal.instance)
    }
}