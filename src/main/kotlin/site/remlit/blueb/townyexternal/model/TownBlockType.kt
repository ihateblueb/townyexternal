package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable

@Serializable
enum class TownBlockType {
    RESIDENTIAL,
    COMMERCIAL,
    ARENA,
    EMBASSY,
    WILDS,
    INN,
    JAIL,
    FARM,
    BANK;

    companion object {
        fun fromTowny(townBlockType: com.palmergames.bukkit.towny.`object`.TownBlockType): TownBlockType = when(townBlockType.name) {
            "Default" -> RESIDENTIAL
            "Shop" -> COMMERCIAL
            "Arena" -> ARENA
            "Embassy" -> EMBASSY
            "Wilds" -> WILDS
            "Inn" -> INN
            "Jail" -> JAIL
            "Farm" -> FARM
            "Bank" -> BANK
            else -> RESIDENTIAL
        }
    }
}