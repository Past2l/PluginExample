package green.healingforest.util

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


class File {
    companion object {
        fun write(path: String, data: String) {
            val file = File("plugins/HealingForest", path)
            if(!file.exists()) {
                if(!file.parentFile.exists()) file.parentFile.mkdirs();
                file.createNewFile();
            }
            val out = BufferedWriter(FileWriter(file))
            out.write(data)
            out.close()
        }

        fun read(path: String): String? {
            val file = File("plugins/HealingForest", path)
            return if (file.exists()) file.readText() else null
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