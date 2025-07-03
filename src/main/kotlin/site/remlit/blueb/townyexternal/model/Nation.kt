package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class Nation(
    val uuid: String,
    val name: String,
    val king: MiniResident,
) {
    companion object {
        fun fromTowny(nation: com.palmergames.bukkit.towny.`object`.Nation): Nation {
            return Nation(
                nation.uuid.toString(),
                nation.name,
                Resident.fromTowny(nation.king).mini(),
            )
        }
    }
}
