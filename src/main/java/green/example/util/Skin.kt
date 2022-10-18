package green.example.util

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class Skin {
    companion object {
        fun getByUUID(uuid: String): SkinResult {
            try {
                val connection =
                    URL("https://sessionserver.mojang.com/session/minecraft/profile/$uuid?unsigned=false").openConnection() as HttpURLConnection
                if (connection.responseCode == 200) {
                    val lines = ArrayList<String>()
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    reader.lines().forEach { e: String -> lines.add(e) }
                    val reply = java.lang.String.join(" ", lines)
                    val texture = Regex("(?<=value\" : \")(.*?)(?=\")").find(reply)
                    val signature = Regex("(?<=signature\" : \")(.*?)(?=\")").find(reply)
                    return SkinResult(texture?.value, signature?.value)
                }
            } catch (e: IOException) {
                e.printStackTrace();
            }
            return SkinResult(null, null)
        }

        fun getByNickname(nickname: String): SkinResult {
            try {
                val connection = URL("https://api.mojang.com/users/profiles/minecraft/$nickname").openConnection() as HttpURLConnection
                if (connection.responseCode == 200) {
                    val lines = ArrayList<String>()
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    reader.lines().forEach { e: String -> lines.add(e) }
                    val reply = java.lang.String.join(" ", lines)
                    val uuid = Regex("(?<=id\":\")(.*?)(?=\")").find(reply) ?: return SkinResult(null, null)
                    return getByUUID(uuid.value)
                }
            } catch (e: IOException) {
                e.printStackTrace();
            }
            return SkinResult(null, null)
        }
    }
}