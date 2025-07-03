package site.remlit.blueb.townyexternal.service

class CacheService {
    companion object {
        enum class CacheType {
            TOWN, NATION, RESIDENT, ALL
        }

        fun get(key: String): String? { return null }
        fun set(key: String, value: String) {}
        fun clear(type: CacheType) {}
    }
}