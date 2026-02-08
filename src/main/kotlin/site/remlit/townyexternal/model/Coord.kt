package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    val x: Double,
    val y: Double,
    val z: Double,
    val world: String
)