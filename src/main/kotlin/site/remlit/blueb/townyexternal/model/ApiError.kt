package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val message: String? = null,
    val stackTrace: String? = null,
)