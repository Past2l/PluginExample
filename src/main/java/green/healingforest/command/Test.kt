package green.healingforest.command

import green.healingforest.command.test.TestGUI
import org.bukkit.ChatColor
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import green.healingforest.entity.Player

class Test: Command(
    arrayOf(TestGUI()),
    true
)