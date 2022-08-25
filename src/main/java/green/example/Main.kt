package green.example

import green.example.custom.command.NPC
import green.example.custom.command.Test
import green.example.entity.Player
import green.example.event.GUIEvent
import green.example.event.NPCEvent
import green.example.event.PlayerEvent
import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        this.initCommands()
        this.initEvents()
        this.initGameRules()
        Player.loadAllData()
        super.onEnable()
    }

    override fun onDisable() {
        Player.saveAllData()
        super.onDisable()
    }

    private fun initCommands() {
        getCommand("test")?.setExecutor(Test())
        getCommand("test")?.tabCompleter = Test()
        getCommand("npc")?.setExecutor(NPC())
        getCommand("npc")?.tabCompleter = NPC()
    }

    private fun initEvents() {
        server.pluginManager.registerEvents(GUIEvent(),this)
        server.pluginManager.registerEvents(PlayerEvent(), this)
        server.pluginManager.registerEvents(NPCEvent(), this)
    }

    private fun initGameRules() {
        for (world in Bukkit.getWorlds()) {
            world.setGameRule(GameRule.KEEP_INVENTORY, true)
            world.setGameRule(GameRule.DISABLE_RAIDS, true)
            world.setGameRule(GameRule.DO_INSOMNIA, false)
            world.setGameRule(GameRule.SPAWN_RADIUS, 0)
            world.setGameRule(GameRule.DO_FIRE_TICK, false)
            world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false)
            world.setGameRule(GameRule.FALL_DAMAGE, true)
        }
    }
}