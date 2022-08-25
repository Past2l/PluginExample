package green.example.util
//
//import org.bukkit.Bukkit
//import org.bukkit.entity.Player
//import org.checkerframework.checker.units.qual.A
//import org.checkerframework.checker.units.qual.C
//
//
//class NMS {
//    companion object {
//        fun getClass(name: String): Class<*>? {
//            return try {
//                val version = Bukkit.getServer().javaClass.packageName.split("\\.")[3]
//                Class.forName("net.minecraft.server.${version}.${name}")
//            } catch(e: ClassNotFoundException) {
//                e.printStackTrace()
//                null
//            }
//        }
//
//        fun sendPacket(player: Player, packet: Any) {
//            try {
//                val handle = player.javaClass.getMethod("getHandle").invoke(player)
//                val playerConnection = handle.javaClass.getField("playerConnection").get(handle)
//                    playerConnection.javaClass.getMethod("sendPacket", getClass("Packet")).invoke(playerConnection, packet)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        fun
//    }
//}