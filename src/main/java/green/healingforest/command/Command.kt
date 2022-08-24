package green.healingforest.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

open class Command @JvmOverloads constructor(
    private val commands: Array<SubCommand>,
    private val onlyOP: Boolean = false
): CommandExecutor, TabExecutor {

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
        } else commands.forEach { if(args[0] == it.name) it.onCommand(sender, command, label, args) }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        if(sender !is org.bukkit.entity.Player) return mutableListOf()
        if(onlyOP && !sender.isOp) return mutableListOf()
        when(args.size) {
            1 -> return commands.map { it.name } as MutableList
            else -> {
                for(it in commands) if(args[args.size-2] == it.name) return it.onTabComplete(sender, command, label, args)
                return mutableListOf()
            }
        }
    }
}