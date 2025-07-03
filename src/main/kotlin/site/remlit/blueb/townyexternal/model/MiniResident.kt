package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MiniResident(
    val uuid: UUID,

    val name: String,
    val title: String? = null,
    val surname: String? = null,

    val lastOnline: Long,
    val registered: Long,
)
