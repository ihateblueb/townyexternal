package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class ChunkCoord(
    val x: Int,
    val z: Int,
)
