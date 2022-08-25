package green.example.event

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.events.PacketEvent
import com.comphenix.protocol.wrappers.EnumWrappers.EntityUseAction
import com.comphenix.protocol.wrappers.EnumWrappers.Hand
import green.example.Main
import green.example.entity.NPC
import me.kodysimpson.simpapi.exceptions.MenuManagerException
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException
import me.kodysimpson.simpapi.menu.MenuManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin


class NPCEvent: Listener {

    companion object {
        fun onNPCClicked() {
            ProtocolLibrary.getProtocolManager().addPacketListener(object : PacketAdapter(JavaPlugin.getPlugin(Main::class.java), PacketType.Play.Client.USE_ENTITY) {
                override fun onPacketReceiving(event: PacketEvent) {
                    val packet: PacketContainer = event.packet
                    val entityID = packet.integers.read(0)
                    val hand = packet.enumEntityUseActions.read(0).hand
                    val action = packet.enumEntityUseActions.read(0).action
                    if (hand == Hand.MAIN_HAND && action == EntityUseAction.INTERACT /* and detect entity id*/) {
                        event.player.sendMessage("$entityID $hand $action")
//                        if (npcManager.getJeffID() === entityID) {
//                            //the packet will happen 4 times, twice for each hand and again twice for INTERACT AND INTERACT_AT
//                        lets choose one to listen for specifically
//                            //open the menu in a synchronous bukkit task
//                            getServer().getScheduler().runTask(plugin) {
//                                //open the quest menu to display the available quests
//                                try {
//                                    MenuManager.openMenu(QuestMenu::class.java, event.getPlayer())
//                                } catch (e: MenuManagerException) {
//                                    e.printStackTrace()
//                                } catch (e: MenuManagerNotSetupException) {
//                                    e.printStackTrace()
//                                }
//                            }
//                        }
                    }
                }
            })
        }
    }

    @EventHandler
    fun onJoined(event: PlayerJoinEvent) {
        NPC.npcMap.values.forEach { npc -> NPC.render(event.player, npc.npc) }
    }
}