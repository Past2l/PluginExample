package green.healingforest.command.npc

import green.healingforest.command.SubCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class NPCSpawn: SubCommand() {
    override val name: String = ""
    override val description: String = ""
    override val syntax: String = ""
    override fun run(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ) {

    }
}