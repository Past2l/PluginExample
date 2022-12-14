package green.example.event

import green.example.Main
import green.example.entity.NPC
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerJoinEvent


class NPCEvent: Listener {
    @EventHandler
    fun onJoined(event: PlayerJoinEvent) {
        NPC.npcMap.values.forEach { npc -> NPC.render(event.player, npc.npc) }
    }
}