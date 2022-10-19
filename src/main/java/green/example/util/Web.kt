package green.example.util

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

class Web {
    companion object {
        fun parse(url: String): String? {
            return try {
                val connection = URL(url).openConnection() as HttpURLConnection
                if (connection.responseCode == 200) {
                    val lines = ArrayList<String>()
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    reader.lines().forEach { e: String -> lines.add(e) }
                    java.lang.String.join(" ", lines)
                } else {
                    print("[ Error ] ${connection.responseCode} : $url")
                    null
                }
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}