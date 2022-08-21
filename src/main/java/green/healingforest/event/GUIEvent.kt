package green.healingforest.event

import green.healingforest.entity.Player
import net.kyori.adventure.text.TextComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class GUIEvent: Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked
        val title = event.view.title()
        val gui = Player.guiMap[player.uniqueId]
        if(title is TextComponent && gui != null && gui.equalsTitle(title)) {
            event.isCancelled = !gui.canClick(event.rawSlot)
            if(event.clickedInventory == event.view.topInventory) gui.getClickEvent(event.rawSlot)?.onClick(event)
        }
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player
        val title = event.view.title()
        val gui = Player.guiMap[player.uniqueId]
        if(title is TextComponent && gui!=null && gui.equalsTitle(title)) gui.getCloseEvent()?.onClose(event)
    }
}