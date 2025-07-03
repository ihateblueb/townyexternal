package site.remlit.blueb.townyexternal.service

import com.palmergames.bukkit.towny.TownyAPI
import site.remlit.blueb.townyexternal.model.Nation

class NationService {
    companion object {
        private val towny = TownyAPI.getInstance()

        fun getNations(): List<Nation> {
            val nations = mutableListOf<Nation>()

            for (nation in towny.nations) {
                nations.add(Nation.fromTowny(nation))
            }

            return nations.toList()
        }
    }
}