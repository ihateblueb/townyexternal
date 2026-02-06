package site.remlit.townyexternal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TownBlockType {
    @SerialName("residential") RESIDENTIAL,
    @SerialName("commercial") COMMERCIAL,
    @SerialName("arena") ARENA,
    @SerialName("embassy") EMBASSY,
    @SerialName("wilds") WILDS,
    @SerialName("inn") INN,
    @SerialName("jail") JAIL,
    @SerialName("farm") FARM,
    @SerialName("bank") BANK;

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