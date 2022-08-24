package green.healingforest.entity

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.minecraft.network.protocol.game.*
import net.minecraft.network.syncher.DataWatcher
import net.minecraft.network.syncher.DataWatcherObject
import net.minecraft.network.syncher.DataWatcherRegistry
import net.minecraft.network.syncher.DataWatcherSerializer
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

    companion object {
        val list = arrayListOf<NPC>()

        fun render(player: Player, npc: EntityPlayer) {
//            val watcher: DataWatcher = npc.ai()
//            watcher.a(DataWatcherObject(16, DataWatcherRegistry.a), 127.toByte())
            val connection = (player as CraftPlayer).handle.b
//            connection.a(PacketPlayOutEntityMetadata(npc.ae(), watcher, true))
            connection.a(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc))
            connection.a(PacketPlayOutNamedEntitySpawn(npc))
            connection.a(PacketPlayOutEntityHeadRotation(npc, (npc.x * 256 / 360).toInt().toByte()))
        }

        fun remove() {
            for(npc in list) npc.remove()
        }
    }

    init {
        this.uuid = uuid ?: UUID.randomUUID()
        val server = (player.server as CraftServer).server
        val world = (player.world as CraftWorld).handle
        val profile = GameProfile(this.uuid, name)
        if(signature != null && texture != null) profile.properties.put("textures", Property("textures", texture, signature))
        npc = EntityPlayer(server, world, profile, null)
        npc.a(player.location.x, player.location.y, player.location.z, player.location.yaw, player.location.pitch)
        list.add(this)
        for(player in Bukkit.getOnlinePlayers()) render(player, npc)
    }

    fun remove() {
        for(player in Bukkit.getOnlinePlayers()) {
            val connection = (player as CraftPlayer).handle.b
            connection.a(PacketPlayOutEntityDestroy(npc.ae()))
        }
    }
}