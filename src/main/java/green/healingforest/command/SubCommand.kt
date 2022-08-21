package green.healingforest.command

import org.bukkit.entity.Player

abstract class SubCommand {
    abstract val name: String
    abstract val description: String
    abstract val syntax: String
    abstract fun run(player: Player, args: Array<out String>)
}
