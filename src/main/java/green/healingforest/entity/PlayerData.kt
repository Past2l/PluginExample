package green.healingforest.entity

import net.kyori.adventure.text.Component

data class PlayerData (
    val prefix: Component,
    val admin: Boolean,
    val money: Int
)