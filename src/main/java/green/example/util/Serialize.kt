package green.example.util

import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectOutputStream
import java.io.ByteArrayOutputStream
import java.util.Base64

class Serialize {
    companion object {
        fun item(item: ItemStack): String {
            val io = ByteArrayOutputStream()
            val os = BukkitObjectOutputStream(io)
            os.writeObject(item)
            os.flush()
            return Base64.getEncoder().encodeToString(io.toByteArray())
        }
    }
}