package green.healingforest.custom.command

import green.healingforest.command.Command
import green.healingforest.custom.command.sub.npc.NPCSpawn

class NPC: Command(
    arrayOf(NPCSpawn()),
    true
)