package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class MiniResident(
    val uuid: String,

    val name: String,
    val title: String? = null,
    val surname: String? = null,

    val lastOnline: Long,
    val registered: Long,
)
