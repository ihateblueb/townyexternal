package site.remlit.blueb.townyexternal.service

import com.palmergames.bukkit.towny.TownyAPI
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import site.remlit.blueb.townyexternal.model.CacheType
import site.remlit.blueb.townyexternal.model.Town

class TownService {
    companion object {
        private val towny = TownyAPI.getInstance()

        fun getTownsFresh(): List<Town> {
            val towns = mutableListOf<Town>()

            for (town in towny.towns) {
                towns.add(Town.fromTowny(town))
            }

            val list = towns.toList()
            CacheService.set(CacheType.TOWNS, Json.encodeToString(ListSerializer(Town.serializer()), list))

            return list
        }

        fun getTowns(): List<Town> {
            val cache = CacheService.get(CacheType.TOWNS)
            return if (cache != null) Json.decodeFromString<List<Town>>(cache) else getTownsFresh()
        }
    }
}