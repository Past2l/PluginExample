package green.healingforest.event

import green.healingforest.entity.Player
import green.healingforest.entity.PlayerData
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.*

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
    fun onChat(event: AsyncPlayerChatEvent) {
        val prefix = Player.dataMap[event.player.uniqueId]?.prefix
        event.format = (if(prefix != null && prefix.isNotEmpty()) "$prefix " else "") + "${event.player.displayName} > ${event.message}"
    }

    @EventHandler
    fun onPlayerInteractByPlayer(event: PlayerInteractAtEntityEvent) {
        if(event.rightClicked !is org.bukkit.entity.Player) return
        val player = Bukkit.getPlayer(event.rightClicked.name) ?: return
        event.player.sendMessage("§a${player.displayName}§r")
        event.player.sendMessage("Sneaking : §a${event.player.isSneaking}§r")
    }
}