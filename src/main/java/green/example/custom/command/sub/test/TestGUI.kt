package green.example.custom.command.sub.test

import green.example.command.SubCommand
import green.example.custom.gui.TestMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestGUI: SubCommand {
    override val commands: MutableList<SubCommand> = mutableListOf()
    override val name: String = "gui"
    override val description: String = "Open the Test GUI"
    override val syntax: String = "/test gui"
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        TestMenu().openTo(sender as Player)
        return true
    }
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        when(args.size) {
            2 -> return commands.map { it.name } as MutableList
            else -> {
                for(it in commands) if(args[args.size-2] == it.name) return it.onTabComplete(sender, command, label, args)
                return mutableListOf()
            }
        }
    }
}