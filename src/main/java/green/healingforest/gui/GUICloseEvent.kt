package green.healingforest.gui

import org.bukkit.event.inventory.InventoryCloseEvent

fun interface GUICloseEvent {
    fun onClose(event: InventoryCloseEvent?)
}