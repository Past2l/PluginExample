package green.example.custom.command

import green.example.command.Command
import green.example.custom.command.sub.npc.NPCSpawn

class NPC: Command(
    arrayOf(NPCSpawn()),
    true
)