package green.example.gui

import org.bukkit.event.inventory.InventoryClickEvent

fun interface GUIClickEvent {
    fun onClick(event: InventoryClickEvent?)
}