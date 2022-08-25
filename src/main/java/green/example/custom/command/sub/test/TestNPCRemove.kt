package green.example.custom.command.sub.test

import green.example.command.SubCommand
import green.example.entity.NPC
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class TestNPCRemove: SubCommand {
    override val commands: MutableList<SubCommand> = mutableListOf()
    override val name: String = "remove"
    override val description: String = "remove npc"
    override val syntax: String = "/test npc remove"
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        NPC.remove()
        sender.sendMessage("removed!")
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        return mutableListOf()
    }
}