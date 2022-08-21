package green.healingforest.util;

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.simple.JSONObject

class JSON {
    companion object {
        fun write(path: String, data: HashMap<String, String>) {
            val json = JSONObject(data).toJSONString()
            File.write(path, json)
        }

        fun read(path: String): HashMap<String, String>? {
            val data = File.read(path) ?: return null
            val jsonObject: JsonObject = JsonParser().parse(data).asJsonObject
            return Gson().fromJson<HashMap<String, String>>(jsonObject.toString(), HashMap::class.java)
        }
    }
}
