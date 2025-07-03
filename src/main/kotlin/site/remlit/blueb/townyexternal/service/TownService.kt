package site.remlit.blueb.townyexternal.service

import com.palmergames.bukkit.towny.TownyAPI
import site.remlit.blueb.townyexternal.model.Town

class TownService {
    companion object {
        private val towny = TownyAPI.getInstance()

        fun getTowns(): List<Town> {
            val towns = mutableListOf<Town>()

            for (town in towny.towns) {
                towns.add(Town.fromTowny(town))
            }

            return towns.toList()
        }
    }
}