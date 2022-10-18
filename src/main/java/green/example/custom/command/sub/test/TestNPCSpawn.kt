package green.example.custom.command.sub.test

import green.example.command.SubCommand
import green.example.entity.NPC
import green.example.util.Skin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class TestNPCSpawn: SubCommand {
    override val commands: MutableList<SubCommand> = mutableListOf()
    override val name: String = "spawn"
    override val description: String = "spawn npc"
    override val syntax: String = "/test npc spawn"
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val nickname = args[2].ifEmpty { "Bbakku_" }
        val skin = Skin.getByNickname(nickname)
        NPC(
            sender as Player,
            nickname,
            null,
            null,
            skin.texture,
            skin.signature
        )
        sender.sendMessage("spawned!")
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