package green.healingforest.custom.command.sub.npc

import green.healingforest.command.SubCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class NPCSpawn: SubCommand() {
    override val name: String = "spawn"
    override val description: String = "Spawn NPCs"
    override val syntax: String = "/npc spawn"
    override fun run(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ) {
        sender.sendMessage("§4:(§r")
    }
}