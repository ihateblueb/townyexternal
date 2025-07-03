package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Nation(
    val uuid: UUID,
    val name: String,
    val king: MiniResident,
) {
    companion object {
        fun fromTowny(nation: com.palmergames.bukkit.towny.`object`.Nation): Nation {
            return Nation(
                nation.uuid,
                nation.name,
                Resident.fromTowny(nation.king).mini(),
            )
        }
    }
}
