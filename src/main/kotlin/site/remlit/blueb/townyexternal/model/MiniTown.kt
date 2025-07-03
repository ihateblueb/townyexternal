package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class MiniTown(
    val uuid: String,
    val name: String,
    val mayor: MiniResident,
    val founder: String,
    val homeBlock: MiniTownBlock
)
