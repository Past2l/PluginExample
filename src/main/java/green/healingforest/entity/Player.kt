package green.healingforest.entity

import green.healingforest.gui.GUI
import green.healingforest.util.Deserialize
import green.healingforest.util.JSON
import green.healingforest.util.Serialize
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import java.util.*
import kotlin.collections.HashMap

class Player {
    companion object {
        val guiMap: HashMap<UUID, GUI> = HashMap()
        val dataMap: HashMap<UUID, PlayerData> = HashMap()

        fun createData(): PlayerData {
            return PlayerData(
                prefix = Component.text(""),
                money = 0,
                popularity = 0
            )
        }

        fun loadData(player: org.bukkit.entity.Player): PlayerData? {
            val data = JSON.read("playerdata/${player.uniqueId}.json")
            return if(data== null) null else PlayerData(
                prefix = Deserialize.prefix(data["prefix"] ?: ""),
                money = data["money"]?.toInt() ?: 0,
                popularity = data["popularity"]?.toInt() ?: 0
            )
        }

        fun saveData(player: org.bukkit.entity.Player) {
            val data = this.dataMap[player.uniqueId]
            val hashMap: HashMap<String, String> = hashMapOf()
            if(data != null) {
                hashMap["prefix"] = Serialize.prefix(data.prefix)
                hashMap["money"] = data.money.toString()
                hashMap["popularity"] = data.popularity.toString()
                JSON.write("playerdata/${player.uniqueId}.json", hashMap)
            }
        }

        fun loadAllData() {
            for(player in Bukkit.getServer().onlinePlayers) this.dataMap[player.uniqueId] = this.loadData(player) ?: this.createData()
        }

        fun saveAllData() {
            for(player in Bukkit.getServer().onlinePlayers) this.saveData(player)
        }
    }
}