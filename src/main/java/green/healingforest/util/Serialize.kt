package green.healingforest.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

class Serialize {
    companion object {
        fun component(data: Component): String {
            return GsonComponentSerializer.gson().serialize(data)
        }

        fun prefix(data: Component): String {
            val text = data as TextComponent
            return LegacyComponentSerializer.legacyAmpersand().serialize(text)
        }

    }
}