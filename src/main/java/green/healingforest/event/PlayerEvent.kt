package green.healingforest.event

import green.healingforest.entity.Player
import green.healingforest.entity.PlayerData
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerEvent: Listener {
    @EventHandler
    fun onJoined(event: PlayerJoinEvent) {
        val data: PlayerData = Player.loadData(event.player) ?: Player.createData()
        Player.dataMap[event.player.uniqueId] = data
    }

    @EventHandler
    fun onLeft(event: PlayerQuitEvent) {
        Player.saveData(event.player)
        Player.dataMap.remove(event.player.uniqueId)
    }

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val prefix = Player.dataMap[event.player.uniqueId]?.prefix
        event.renderer { player, _, component, _ ->
            Component.text("")
                .append(
                    prefix?.append(
                        Component.text(" ").color(TextColor.color(0xffffff))
                    ) ?: Component.text("")
                )
                .append(Component.text("").color(TextColor.color(0xffffff)))
                .append(player.displayName())
                .append(Component.text(" > ").color(TextColor.color(0xffffff)))
                .append(component)
        }
    }
}