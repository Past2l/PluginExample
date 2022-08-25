package green.example.custom.command.sub.npc

import green.example.command.SubCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class NPCSpawn: SubCommand {
    override val commands: MutableList<SubCommand> = mutableListOf()
    override val name: String = "spawn"
    override val description: String = "Spawn NPCs"
    override val syntax: String = "/npc spawn"
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        sender.sendMessage("§4:(§r")
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