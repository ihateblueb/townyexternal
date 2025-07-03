package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Town(
    val uuid: UUID,
    val name: String,
    val mayor: MiniResident,
    val founder: String,
    //val nation: Nation,
    val homeBlock: TownBlock
) {
    companion object {
        fun fromTowny(town: com.palmergames.bukkit.towny.`object`.Town): Town {
            return Town(
                town.uuid,
                town.name,
                Resident.fromTowny(town.mayor).mini(),
                town.founder,
                TownBlock.fromTowny(town.homeBlock)
            )
        }
    }
}
