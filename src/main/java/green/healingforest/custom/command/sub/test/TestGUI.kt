package green.healingforest.custom.command.sub.test

import green.healingforest.command.SubCommand
import green.healingforest.custom.gui.TestMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestGUI: SubCommand() {
    override val name: String = "gui"
    override val description: String = "Open the Test GUI"
    override val syntax: String = "/test gui"
    override fun run(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ) {
        TestMenu().openTo(sender as Player)
    }
}