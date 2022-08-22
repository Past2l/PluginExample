package green.healingforest.util

import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import java.io.ByteArrayInputStream
import java.util.Base64

class Deserialize {
    companion object {
        fun item(item: String): ItemStack {
            val io = ByteArrayInputStream(Base64.getDecoder().decode(item))
            val os = BukkitObjectInputStream(io)
            return os.readObject() as ItemStack
        }
    }
}