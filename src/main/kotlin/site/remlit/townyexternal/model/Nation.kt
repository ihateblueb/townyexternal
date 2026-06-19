package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable
import com.palmergames.bukkit.towny.`object`.Nation as TownyNation

@Serializable
data class Nation(
    val uuid: String,
    val name: String,
    val king: MiniResident,

    val capitol: MiniTown,

    val allies: List<MiniNation>,
    val enemies: List<MiniNation>,
) {
    companion object {
        fun fromTowny(nation: TownyNation): Nation {
            return Nation(
                nation.uuid.toString(),
                nation.name,
                Resident.mini(nation.king),
                Town.mini(nation.capital),
                nation.allies.map { mini(it) },
                nation.enemies.map { mini(it) }
            )
        }

        fun mini(nation: TownyNation): MiniNation {
            return MiniNation(
                nation.uuid.toString(),
                nation.name,
                Resident.mini(nation.king)
            )
        }
    }
}
