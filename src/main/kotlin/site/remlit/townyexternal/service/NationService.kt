package site.remlit.townyexternal.service

import com.palmergames.bukkit.towny.TownyAPI
import kotlinx.serialization.json.Json
import site.remlit.townyexternal.model.Nation
import java.util.UUID

class NationService {
    companion object {
        private val towny = TownyAPI.getInstance()

        fun getNationsFresh(): List<Nation> {
            val nations = mutableListOf<Nation>()
            val uuids = mutableListOf<String>()

            for (nation in towny.nations) {
                val value = Nation.fromTowny(nation)
                nations.add(value)
                CacheService.set("nation_${nation.uuid}", Json.encodeToString(value))
                uuids.add(nation.uuid.toString())
            }

            CacheService.set("nations", Json.encodeToString(uuids))

            return nations.toList()
        }

        fun getNationFresh(uuid: String): Nation? {
            val nation = towny.getNation(UUID.fromString(uuid)) ?: return null
            val value = Nation.fromTowny(nation)
            CacheService.set("nation_${nation.uuid}", Json.encodeToString(value))
            return value
        }

        fun getNation(uuid: String): Nation? {
            val cache = CacheService.get("nation_$uuid")
            return if (cache != null) Json.decodeFromString<Nation>(cache) else getNationFresh(uuid)
        }

        fun getNations(): List<Nation> {
            val cache = CacheService.get("nations")
            return if (cache != null) Json.decodeFromString<List<String>>(cache).let {
                val nations = mutableListOf<Nation>()
                for(item in it) {
                    val nation = getNation(item)
                    if (nation != null)
                    nations.add(nation)
                }
                nations.toList()
            } else getNationsFresh()
        }
    }
}