package site.remlit.blueb.townyexternal.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class TownBlock(
    val town: Town,
    val resident: MiniResident? = null,
    val type: TownBlockType,
    val coord: Pair<Int, Int>,
    val price: Double,
    val outpost: Boolean = false,
    // val group
    // val district
    val claimedAt: Long,
) {
    fun mini() = mini(this)
    companion object {
        fun fromTowny(townBlock: com.palmergames.bukkit.towny.`object`.TownBlock): TownBlock {
            return TownBlock(
                Town.fromTowny(townBlock.town),
                Resident.fromTowny(townBlock.resident).mini(),
                TownBlockType.fromTowny(townBlock.type),
                Pair(townBlock.coord.x, townBlock.coord.z),
                townBlock.plotPrice,
                townBlock.isOutpost,
                townBlock.claimedAt,
            )
        }

        fun mini(townBlock: TownBlock): MiniTownBlock {
            return MiniTownBlock(
                townBlock.resident,
                townBlock.type,
                townBlock.coord,
                townBlock.price,
                townBlock.outpost,
                townBlock.claimedAt,
            )
        }
    }
}
