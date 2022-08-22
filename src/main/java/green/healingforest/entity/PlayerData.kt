package green.healingforest.entity

import net.kyori.adventure.text.Component

data class PlayerData (
    val prefix: Component,
    val money: Int,
    val popularity: Int
)