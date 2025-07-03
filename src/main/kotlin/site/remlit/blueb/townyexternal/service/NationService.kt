package site.remlit.blueb.townyexternal.service

import com.palmergames.bukkit.towny.TownyAPI
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import site.remlit.blueb.townyexternal.model.CacheType
import site.remlit.blueb.townyexternal.model.Nation
import site.remlit.blueb.townyexternal.model.Town

class NationService {
    companion object {
        private val towny = TownyAPI.getInstance()

        fun getNationsFresh(): List<Nation> {
            val nations = mutableListOf<Nation>()

            for (nation in towny.nations) {
                nations.add(Nation.fromTowny(nation))
            }

            val list = nations.toList()
            CacheService.set(CacheType.NATIONS, Json.encodeToString(ListSerializer(Nation.serializer()), list))

            return list
        }

        fun getNations(): List<Nation> {
            val cache = CacheService.get(CacheType.NATIONS)
            return if (cache != null) Json.decodeFromString<List<Nation>>(cache) else getNationsFresh()
        }
    }
}