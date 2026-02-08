package site.remlit.townyexternal.model

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException
import kotlinx.serialization.Serializable

@Serializable
data class Town(
    val uuid: String,
    val name: String,
    val board: String,

    val mayor: MiniResident? = null,
    val founder: String,
    val nation: MiniNation? = null,

    val spawn: Coord,
    val homeBlock: MiniTownBlock,

    val open: Boolean,
    val capital: Boolean,

    val residents: Int,
    val size: Int,
) {
    companion object {
        fun fromTowny(town: com.palmergames.bukkit.towny.`object`.Town): Town {
            return Town(
                town.uuid.toString(),
                town.name,
                town.board,

                Resident.mini(town.mayor),
                town.founder,
                try { Nation.mini(town.nation) } catch (e: NotRegisteredException) { null },

                Coord(town.spawn.x, town.spawn.y, town.spawn.z, town.spawn.world.name),
                TownBlock.mini(town.homeBlock),

                town.isOpen,
                town.isCapital,

                town.numResidents,
                town.numTownBlocks,
            )
        }

        fun mini(town: com.palmergames.bukkit.towny.`object`.Town): MiniTown {
            return MiniTown(
                town.uuid.toString(),
                town.name,
                Resident.mini(town.mayor),
                town.founder,
                TownBlock.mini(town.homeBlock)
            )
        }
    }
}
