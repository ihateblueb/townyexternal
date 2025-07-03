package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class Resident(
    val uuid: String,

    val name: String,
    val title: String? = null,
    val surname: String? = null,

    val lastOnline: Long,
    val registered: Long,
    var town: Town? = null,
    val joinedTownAt: Long? = null,
) {
    fun mini() = mini(this)
    companion object {
        fun fromTowny(resident: com.palmergames.bukkit.towny.`object`.Resident): Resident {
            return Resident(
                resident.uuid.toString(),
                resident.name,
                resident.title?.ifBlank { null },
                resident.surname?.ifBlank { null },
                resident.lastOnline,
                resident.registered,
                Town.fromTowny(resident.town),
                resident.joinedTownAt
            )
        }

        fun mini(resident: Resident): MiniResident {
            return MiniResident(
                resident.uuid,
                resident.name,
                resident.title?.ifBlank { null },
                resident.surname?.ifBlank { null },
                resident.lastOnline,
                resident.registered,
            )
        }

        fun mini(resident: com.palmergames.bukkit.towny.`object`.Resident): MiniResident {
            return MiniResident(
                resident.uuid.toString(),
                resident.name,
                resident.title?.ifBlank { null },
                resident.surname?.ifBlank { null },
                resident.lastOnline,
                resident.registered,
            )
        }
    }
}

