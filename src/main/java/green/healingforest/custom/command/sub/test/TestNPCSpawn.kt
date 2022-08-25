package green.healingforest.custom.command.sub.test

import green.healingforest.command.SubCommand
import green.healingforest.entity.NPC
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

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
        NPC(
            sender as Player,
            "Bbakku_",
            null,
            null,
            "ewogICJ0aW1lc3RhbXAiIDogMTY2MTMyNzg2MDU4MSwKICAicHJvZmlsZUlkIiA6ICI2OGZmNDA4OTc5Nzg0NmRkYTA2YzU0ODcyNGRkMDliMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCYmFra3VfIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2M2MzE5MGNmYzcxNTU2ZGMzMzY3OGFkMDQ4YWEzMjk5Y2QwZmQzOTVlNzRiZWQ0MTYxYTE3NTU4YTUwZTA5ZGYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
            "JN7hma53WH+Z+yaB4PuER43pz1NfxLk92Dih62QWtHhfFSYVIpWaONRBHb1Tv2JYhl0shI6kB4bPgSx+0CVXNZmEPZ9yQpgjREnMLSo3T6qo9bRlxAHjadE2E1hwa3uHzB+X+EPXKUaXh/xbSfatN3w3fzhg6GWyIV0gIiVuyhn7EwXsw8TnSC4iQva4oaVgC7xz/raYeYRGqTOu6qj0lcXQQi/Eem7MemuiNRTSN3JvOxo7dmC3n4qLXCuLlRQEvIvj/zyl1S8ZBkjREC5GoHZvgO27nBqe4EN088mHeI1IvHpBqhnuOGC64CmAU0oR3UCmFISD4JeTUxYbc6YpAHGpIHygVwTAwItl342uP4uLqAU96/KaEaPLGl8ESAGja3jdNwn/3JPlvsWwOhaAwtXoWzHrFW9O/RqbA4Cnu4Nrs4MqsOz4lsOLntDpbmx3UgPznOiXwlsmkyBHvhKPgd4zTFFHJol/M4ExrJceF7XdZ4h/Tpkw62rIyPi8MWgOW2d+wKtXIczgBxyxPaqKt22dz0EsPWMIXLEhUkFFMN1/QAjl+8AlBhP5M8ZFlfmCt0C8nccSzLfJdV/34mol+KVLIDcrBiuZEzBn8puNhwDGWbI7Al2D5oBdfUTNjZCp5Wy5drnjqBkS9IBZINQm/x4Als9MvIb7suwfok2nABQ="
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