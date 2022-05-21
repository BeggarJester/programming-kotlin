package coursework.view

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// class SerializerManager for convert string in json format
class SerializerManager {

    private val json = Json {
        prettyPrint = true
    }

    fun serialization(list: List<Pair<String, Int>>): String {
        return json.encodeToString(list)
    }

    fun deserialization(data: String): List<Pair<String, Int>> {
        return json.decodeFromString(data)
    }

}