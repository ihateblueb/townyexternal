package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class MiniNation(
    val uuid: String,
    val name: String,
    val king: MiniResident,
)