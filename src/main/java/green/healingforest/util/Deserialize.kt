package green.healingforest.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

class Deserialize {
    companion object {
        fun component(data: String): Component {
            return GsonComponentSerializer.gson().deserialize(data);
        }

        fun prefix(data: String): Component {
            return LegacyComponentSerializer.legacyAmpersand().deserialize(data)
        }
    }
}