package green.healingforest.command

import green.healingforest.command.npc.NPCSpawn

class NPC: Command(
    arrayOf(NPCSpawn()),
    true
)