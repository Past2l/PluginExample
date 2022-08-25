package green.example.gui

import org.bukkit.entity.Player

fun interface GUIOpenEvent {
    fun onOpen(event: GUI, player: Player)
}