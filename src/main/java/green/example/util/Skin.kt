package green.example.util


class Skin {
    companion object {
        fun getByUUID(uuid: String): SkinResult {
            val content = Web.parse("https://sessionserver.mojang.com/session/minecraft/profile/$uuid?unsigned=false") ?: return SkinResult(null, null)
            val texture = Regex("(?<=value\" : \")(.*?)(?=\")").find(content)
            val signature = Regex("(?<=signature\" : \")(.*?)(?=\")").find(content)
            return SkinResult(texture?.value, signature?.value)
        }

        fun getByNickname(nickname: String): SkinResult {
            val content = Web.parse("https://api.mojang.com/users/profiles/minecraft/$nickname") ?: return SkinResult(null, null)
            val uuid = Regex("(?<=id\":\")(.*?)(?=\")").find(content) ?: return SkinResult(null, null)
            return getByUUID(uuid.value)
        }
    }
}