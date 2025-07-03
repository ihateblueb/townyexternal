package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
enum class CacheType {
    TOWNS, NATIONS, RESIDENTS, ALL
}