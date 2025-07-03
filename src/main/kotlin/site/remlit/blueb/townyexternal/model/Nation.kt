package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class Nation(
    val name: String,
    val king: Resident,
    val founder: String,
)
