package green.example.entity

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.minecraft.network.protocol.game.*
import net.minecraft.network.protocol.game.PacketPlayOutEntity.PacketPlayOutEntityLook
import net.minecraft.network.syncher.DataWatcher
import net.minecraft.server.level.EntityPlayer
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.CraftServer
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*

class NPC(
    private val player: Player,
    name: String,
    uuid: UUID? = null,
    clickEvent: Any? = null,
    texture: String? = null,
    signature: String? = null
) {
    val npc: EntityPlayer
    val uuid: UUID
    val id: Int

    companion object {
        var npcMap = hashMapOf<Int, NPC>()

        fun render(player: Player, npc: EntityPlayer) {
            val watcher: DataWatcher = npc.ai()
            watcher.registrationLocked = false
            val connection = (player as CraftPlayer).handle.b
            val yaw = (player.location.yaw * 256 / 360).toInt().toByte()
            val pitch = (player.location.pitch * 256 / 360).toInt().toByte()
            connection.a(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc))
            connection.a(PacketPlayOutNamedEntitySpawn(npc))
            connection.a(PacketPlayOutEntityHeadRotation(npc, yaw))
            connection.a(PacketPlayOutEntityLook(npc.ae(), yaw, pitch, true))
//            watcher.a(DataWatcherRegistry.a.a(16), 127.toByte())
//            connection.a(PacketPlayOutEntityMetadata(npc.ae(), watcher, true))
        }

        fun remove() {
            npcMap.values.forEach { npc ->
                for(player in Bukkit.getOnlinePlayers()) {
                    val connection = (player as CraftPlayer).handle.b
                    connection.a(PacketPlayOutEntityDestroy(npc.id))
                }
            }
            npcMap = hashMapOf()
        }
    }

    init {
        this.uuid = uuid ?: UUID.randomUUID()
        val server = (player.server as CraftServer).server
        val world = (player.world as CraftWorld).handle
        val profile = GameProfile(this.uuid, name)
        if(signature != null && texture != null) profile.properties.put("textures", Property("textures", texture, signature))
        npc = EntityPlayer(server, world, profile, null)
        this.id = npc.ae()
        npc.a(player.location.x, player.location.y, player.location.z, player.location.yaw, player.location.pitch)
        npcMap[this.id] = this
        for(player in Bukkit.getOnlinePlayers()) render(player, npc)
    }

    fun remove() {
        for(player in Bukkit.getOnlinePlayers()) {
            val connection = (player as CraftPlayer).handle.b
            connection.a(PacketPlayOutEntityDestroy(this.id))
            npcMap.remove(this.id)
        }
    }
}