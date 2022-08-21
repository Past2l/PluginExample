package green.healingforest.gui

import net.kyori.adventure.text.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class GUI @JvmOverloads constructor(
    private val title: TextComponent,
    lines: Int,
    clickAll: Boolean,
    click: Array<Array<Boolean>?>? = null,
    openEvent: GUIOpenEvent? = null,
    closeEvent: GUICloseEvent? = null,
    owner: InventoryHolder? = null
) {
    private val inventory: Inventory
    private var openEvent: GUIOpenEvent?
    private val click: Array<Array<Boolean>?>?
    private val clickAll: Boolean
    private val closeEvent: GUICloseEvent?

    companion object {
        val clickEvent: HashMap<Int, GUIClickEvent> = HashMap<Int, GUIClickEvent>()
    }

    init {
        inventory = Bukkit.createInventory(owner, lines * 9, title)
        this.openEvent = openEvent
        this.closeEvent = closeEvent
        this.click = click
        this.clickAll = clickAll
    }

    fun getItem(slot: Int): ItemStack? {
        return inventory.getItem(slot)
    }

    fun setItem(slot: Int, item: ItemStack, event: GUIClickEvent?) {
        val meta = item.itemMeta
        if (meta != null) {
            for (flag in ItemFlag.values()) meta.addItemFlags(flag)
            item.itemMeta = meta
        }
        inventory.setItem(slot, item)
        if(event != null) setClickEvent(slot, event)
    }

    fun setItem(item: Array<Array<ItemStack?>?>, clickEvent: Array<Array<GUIClickEvent?>?>? = null) {
        for(i in item.indices) {
            val list = item[i]
            if(list != null) for(j in list.indices) {
                val data = list[j]
                val event = clickEvent?.get(i)?.get(j)
                if(data != null) setItem(i*9+j, data, event)
            }
        }
    }

    fun getInventory(): Inventory {
        return inventory
    }

    fun getClickEvent(slot: Int): GUIClickEvent? {
        return clickEvent[slot]
    }

    fun setClickEvent(slot: Int, event: GUIClickEvent) {
        clickEvent[slot] = event
    }

    fun getCloseEvent(): GUICloseEvent? {
        return closeEvent
    }

    fun canClick(slot: Int): Boolean {
        return if(click == null) clickAll else click[slot/9]!![slot%9]
    }

    fun equalsTitle(title: TextComponent?): Boolean {
        return this.title.content() == title!!.content()
    }

    fun openTo(player: Player) {
        player.openInventory(inventory)
        green.healingforest.entity.Player.guiMap[player.uniqueId] = this
        this.openEvent?.onOpen(this, player)
    }
}