package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class Town(
    val name: String,
    val mayor: Resident,
    val founder: String,
    //val nation: Nation,
)
