package green.example.util

import green.example.Main
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class File {
    companion object {
        fun write(path: String, data: String) {
            val file = File(JavaPlugin.getPlugin(Main::class.java).dataFolder, path)
            if(!file.exists()) {
                if(!file.parentFile.exists()) file.parentFile.mkdirs();
                file.createNewFile();
            }
            FileOutputStream(file).use { fos ->
                OutputStreamWriter(fos, Charsets.UTF_8).use { osw ->
                    BufferedWriter(osw).use { bf -> bf.write(data) }
                }
            }
        }

        fun read(path: String): String? {
            val file = File(JavaPlugin.getPlugin(Main::class.java).dataFolder, path)
            return if (file.exists()) file.readText(Charsets.UTF_8) else null
        }

        fun list(path: String): Array<String> {
            val res: MutableList<String> = mutableListOf()
            val file = File("plugins/HealingForest", path)
            if(!file.exists()) return res.toTypedArray()
            val list = file.listFiles() ?: return res.toTypedArray()
            for(data in list) res.add(data.name)
            return res.toTypedArray()
        }

        fun exist(path: String): Boolean {
            return File("plugins/HealingForest", path).exists()
        }
    }

}