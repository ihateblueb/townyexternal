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
    //val town: Town? = null,
    val joinedTownAt: Long? = null,
)
