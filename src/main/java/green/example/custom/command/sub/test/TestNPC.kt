package green.example.custom.command.sub.test

import green.example.command.SubCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender


class TestNPC: SubCommand {
    override val commands: MutableList<SubCommand> = mutableListOf(TestNPCSpawn(),TestNPCRemove())
    override val name: String = "npc"
    override val description: String = "test npc"
    override val syntax: String = "/test npc"
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if(args.size <= 1) {
            sender.sendMessage("§e---------------§r Help: $syntax §e---------------")
            commands.forEach { sender.sendMessage("§6${it.syntax}: §r${it.description}")}
        } else commands.forEach { if(args[1] == it.name) it.onCommand(sender, command, label, args) }
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