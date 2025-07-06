package site.remlit.blueb.townyexternal

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import com.palmergames.bukkit.towny.TownyAPI
import org.bukkit.command.CommandSender

@Suppress("Unused")
@CommandAlias("townyexternal|te")
@CommandPermission("townyexternal.command")
class Commands : BaseCommand() {
    private val towny = TownyAPI.getInstance()

    @Default
    fun default(sender: CommandSender) {
        sender.sendMessage("Running TownyExternal version ${TownyExternal.instance.description.version}")
    }

    companion object {
        fun register() = TownyExternal.commandManager.registerCommand(Commands())
    }
}