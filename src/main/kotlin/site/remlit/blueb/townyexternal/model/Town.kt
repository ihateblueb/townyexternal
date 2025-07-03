package site.remlit.blueb.townyexternal.model

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException
import kotlinx.serialization.Serializable

@Serializable
data class Town(
    val uuid: String,
    val name: String,
    val mayor: MiniResident? = null,
    val founder: String,
    val nation: MiniNation? = null,
    val homeBlock: MiniTownBlock
) {
    companion object {
        fun fromTowny(town: com.palmergames.bukkit.towny.`object`.Town): Town {
            return Town(
                town.uuid.toString(),
                town.name,
                Resident.mini(town.mayor),
                town.founder,
                try { Nation.mini(town.nation) } catch (e: NotRegisteredException) { null },
                TownBlock.mini(town.homeBlock)
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
