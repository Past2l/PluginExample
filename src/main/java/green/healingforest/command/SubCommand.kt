package green.healingforest.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

abstract class SubCommand {
    abstract val name: String
    abstract val description: String
    abstract val syntax: String
    abstract fun run(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    )
}
