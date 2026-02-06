package site.remlit.townyexternal.service

import com.palmergames.bukkit.towny.TownyAPI
import kotlinx.serialization.json.Json
import site.remlit.townyexternal.model.Town
import java.util.UUID

class TownService {
    companion object {
        private val towny = TownyAPI.getInstance()

        fun getTownsFresh(): List<Town> {
            val towns = mutableListOf<Town>()
            val uuids = mutableListOf<String>()

            for (town in towny.towns) {
                val value = Town.fromTowny(town)
                towns.add(value)
                CacheService.set("town_${town.uuid}", Json.encodeToString(Town.serializer(), value))
                uuids.add(town.uuid.toString())
            }

            CacheService.set("towns", Json.encodeToString(uuids))

            return towns.toList()
        }

        fun getTownFresh(uuid: String): Town? {
            val town = towny.getTown(UUID.fromString(uuid)) ?: return null
            val value = Town.fromTowny(town)
            CacheService.set("town_${town.uuid}", Json.encodeToString(Town.serializer(), value))
            return value
        }

        fun getTown(uuid: String): Town? {
            val cache = CacheService.get("town_$uuid")
            return if (cache != null) Json.decodeFromString<Town>(cache) else getTownFresh(uuid)
        }

        fun getTowns(): List<Town> {
            val cache = CacheService.get("towns")
            return if (cache != null) Json.decodeFromString<List<String>>(cache).let {
                val towns = mutableListOf<Town>()
                for(item in it) {
                    val town = getTown(item)
                    if (town != null)
                        towns.add(town)
                }
                towns.toList()
            } else getTownsFresh()
        }
    }
}