package green.healingforest.command

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

open class Command @JvmOverloads constructor(
    commands: Array<SubCommand>,
    onlyOP: Boolean = false
): CommandExecutor, TabExecutor {
    private val commands: Array<SubCommand>
    private val onlyOP: Boolean

    init {
        this.commands = commands
        this.onlyOP = onlyOP
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if(sender !is org.bukkit.entity.Player) return false
        if(onlyOP && !sender.isOp) {
            sender.sendMessage("§4Permission Denied.§r")
            return false
        } else if(args.isEmpty()) {
            sender.sendMessage("§e---------------§r Help: /$label §e---------------")
            commands.forEach { sender.sendMessage("§6${it.syntax}: §r${it.description}")}
        } else commands.forEach { if(args[0] == it.name) it.run(sender, command, label, args) }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        if(sender !is org.bukkit.entity.Player) return mutableListOf()
        return if(onlyOP && !sender.isOp) mutableListOf()
        else when(args.size) {
            1 -> commands.map { it.name } as MutableList
            else -> mutableListOf()
        }
    }
}