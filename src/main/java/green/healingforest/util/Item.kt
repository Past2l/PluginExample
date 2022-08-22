package green.healingforest.util

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class Item @JvmOverloads constructor(
    material: Material,
    amount: Int = 1
) {
    private val item: ItemStack

    init {
        item = ItemStack(material, amount)
    }

    fun setName(name: String): ItemStack {
        val itemMeta = item.itemMeta
        itemMeta?.setDisplayName(name)
        item.itemMeta = itemMeta
        return item
    }

    fun setLore(lore: MutableList<String>): ItemStack {
        val itemMeta = item.itemMeta
        itemMeta?.lore = lore
        item.itemMeta = itemMeta
        return item
    }

    fun setEnchantment(
        enchantment: Enchantment,
        level: Int
    ): ItemStack {
        val itemMeta = item.itemMeta
        itemMeta?.addEnchant(enchantment, level, true)
        item.itemMeta = itemMeta
        return item
    }

    fun setEnchantments(enchantments: Map<Enchantment, Int>): ItemStack {
        val itemMeta = item.itemMeta
        for(e in enchantments) itemMeta?.addEnchant(e.key, e.value, true)
        item.itemMeta = itemMeta
        return item
    }

    fun setItemFlag(flag: ItemFlag): ItemStack {
        val itemMeta = item.itemMeta
        itemMeta?.addItemFlags(flag)
        item.itemMeta = itemMeta
        return item
    }

    fun setItemFlags(flags: List<ItemFlag>): ItemStack {
        val itemMeta = item.itemMeta
        for(e in flags) itemMeta?.addItemFlags(e)
        item.itemMeta = itemMeta
        return item
    }

    fun glow(): ItemStack {
        val itemMeta = item.itemMeta
        itemMeta?.addEnchant(Enchantment.DURABILITY, 1, true)
        itemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        item.itemMeta = itemMeta
        return item
    }
}