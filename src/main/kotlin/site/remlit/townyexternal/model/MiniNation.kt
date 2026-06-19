package site.remlit.townyexternal.model

import kotlinx.serialization.Serializable
import com.palmergames.bukkit.towny.`object`.Nation as TownyNation

@Serializable
data class MiniNation(
    val uuid: String,
    val name: String,
    val king: MiniResident,
)