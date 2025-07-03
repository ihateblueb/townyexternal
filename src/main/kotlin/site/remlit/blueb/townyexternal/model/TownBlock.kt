package site.remlit.blueb.townyexternal.model

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException
import kotlinx.serialization.Serializable

@Serializable
data class TownBlock(
    val town: Town,
    val resident: MiniResident? = null,
    val type: TownBlockType,
    val coord: Coord,
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
                try { Resident.mini(townBlock.resident) } catch (e: Exception) { null },
                TownBlockType.fromTowny(townBlock.type),
                Coord(townBlock.coord.x, townBlock.coord.z),
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

        fun mini(townBlock: com.palmergames.bukkit.towny.`object`.TownBlock): MiniTownBlock {
            return MiniTownBlock(
                try { Resident.mini(townBlock.resident) } catch (e: Exception) { null },
                TownBlockType.fromTowny(townBlock.type),
                Coord(townBlock.coord.x, townBlock.coord.z),
                townBlock.plotPrice,
                townBlock.isOutpost,
                townBlock.claimedAt,
            )
        }
    }
}
