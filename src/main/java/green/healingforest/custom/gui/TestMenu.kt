package green.healingforest.custom.gui

import green.healingforest.gui.GUI
import green.healingforest.gui.GUIClickEvent
import green.healingforest.util.Item
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TestMenu {
    private val gui = GUI(
        "test gui",
        6,
        false,
        openEvent = { _, player ->
            player.sendMessage("§4Opened§r Test GUI")
        },
        closeEvent = { event ->
            event!!.player.sendMessage("§4Closed§r Test GUI")
        }
    )
    private val item: Array<Array<ItemStack?>?> = arrayOfNulls(6)
    private val event: Array<Array<GUIClickEvent?>?> = arrayOfNulls(6)

    init {
        // Set GUI Null Items
        for(i in 0..5) item[i] = arrayOfNulls(9)
        for(i in 0..5) event[i] = arrayOfNulls(9)

        val dummy = Item(Material.BARRIER, 1)
            .setName("§a§lClick This!§r")

        for(i in 0..53) {
            item[i/9]!![i%9] = dummy
            event[i/9]!![i%9] = {
                it!!.whoClicked.sendMessage(arrayOf(
                    (if(it.click.isShiftClick) "§aShift§r + " else "") + "§a" + (if(it.click.isRightClick) "Right" else "Left") + "§r",
                    "Clicked Item Slot in §a$i §4(§a${i/9}§r, §a${i%9}§4)§r"
                ).joinToString(" "))
            }
        }

        gui.setItem(item, event)
    }
    fun openTo(player: Player) {
        this.gui.openTo(player)
    }

}