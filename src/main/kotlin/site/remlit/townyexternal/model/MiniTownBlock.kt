package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class MiniTownBlock(
    val resident: MiniResident? = null,
    val type: TownBlockType,
    val coord: ChunkCoord,
    val price: Double,
    val outpost: Boolean = false,
    // val group
    // val district
    val claimedAt: Long,
)
