package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    val x: Int,
    val z: Int,
)
