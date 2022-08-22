package green.healingforest.custom.gui

import green.healingforest.gui.GUI
import green.healingforest.gui.GUIClickEvent
import green.healingforest.util.Item
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TestMenu {
    private val gui = GUI(
        Component.text("test gui"),
        6,
        false,
        openEvent = { _, player ->
            player.sendMessage("${ChatColor.RED}Opened${ChatColor.RESET} Test GUI")
        },
        closeEvent = { event ->
            event!!.player.sendMessage("${ChatColor.RED}Closed${ChatColor.RESET} Test GUI")
        }
    )
    private val item: Array<Array<ItemStack?>?> = arrayOfNulls(6)
    private val event: Array<Array<GUIClickEvent?>?> = arrayOfNulls(6)

    init {
        // Set GUI Null Items
        for(i in 0..5) item[i] = arrayOfNulls(9)
        for(i in 0..5) event[i] = arrayOfNulls(9)

        val dummy = Item(Material.BARRIER, 1)
            .setName(
                Component.text("Click This!")
                    .decoration(TextDecoration.ITALIC, false)
                    .decoration(TextDecoration.BOLD, true)
                    .color(TextColor.color(0x00ff00))
            )

        for(i in 0..53) {
            item[i/9]!![i%9] = dummy
            event[i/9]!![i%9] = {
                it!!.whoClicked.sendMessage(arrayOf(
                    (if(it.click.isShiftClick) "${ChatColor.GREEN}Shift${ChatColor.RESET} + " else "") + ChatColor.GREEN.toString() + (if(it.click.isRightClick) "Right" else "Left") + ChatColor.RESET.toString(),
                    "Clicked Item Slot in",
                    ChatColor.GREEN.toString() + i,
                    "${ChatColor.RED}(${ChatColor.GREEN}${i/9}${ChatColor.RESET}, ${ChatColor.GREEN}${i%9}${ChatColor.RED})${ChatColor.RESET}"
                ).joinToString(" "))
            }
        }

        gui.setItem(item, event)
    }
    fun openTo(player: Player) {
        this.gui.openTo(player)
    }

}