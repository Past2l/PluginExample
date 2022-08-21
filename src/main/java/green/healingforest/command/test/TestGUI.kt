package green.healingforest.command.test

import green.healingforest.command.SubCommand
import green.healingforest.custom.gui.TestMenu
import org.bukkit.entity.Player

class TestGUI: SubCommand() {
    override val name: String = "gui"
    override val description: String = "Open the Test GUI"
    override val syntax: String = "/test gui"

    override fun perform(player: Player, args: Array<out String>) {
        TestMenu().openTo(player)
    }
}