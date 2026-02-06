package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class Nation(
    val uuid: String,
    val name: String,
    val king: MiniResident,

    val capitol: MiniTown,

    val allies: List<Nation>,
    val enemies: List<Nation>,

) {
    companion object {
        fun fromTowny(nation: com.palmergames.bukkit.towny.`object`.Nation): Nation {
            val allies = mutableListOf<Nation>()
            nation.allies.forEach {
                allies.add(fromTowny(it))
            }

            val enemies = mutableListOf<Nation>()
            nation.enemies.forEach {
                enemies.add(fromTowny(it))
            }

            return Nation(
                nation.uuid.toString(),
                nation.name,
                Resident.mini(nation.king),
                Town.mini(nation.capital),
                allies,
                enemies
            )
        }

        fun mini(nation: com.palmergames.bukkit.towny.`object`.Nation): MiniNation {
            return MiniNation(
                nation.uuid.toString(),
                nation.name,
                Resident.mini(nation.king)
            )
        }
    }
}
