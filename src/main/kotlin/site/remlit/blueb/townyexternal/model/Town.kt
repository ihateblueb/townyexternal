package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Town(
    val uuid: String,
    val name: String,
    val mayor: MiniResident,
    val founder: String,
    //val nation: Nation,
    val homeBlock: MiniTownBlock
) {
    companion object {
        fun fromTowny(town: com.palmergames.bukkit.towny.`object`.Town): Town {
            return Town(
                town.uuid.toString(),
                town.name,
                Resident.mini(town.mayor),
                town.founder,
                TownBlock.mini(town.homeBlock)
            )
        }
    }
}
