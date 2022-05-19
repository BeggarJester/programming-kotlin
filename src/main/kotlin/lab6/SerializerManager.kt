package lab6

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lab2.ColoredShape2d
import lab2.libSerializer

// class SerializerManager for convert string in json format
class SerializerManager {

    private val json = Json {
        prettyPrint = true
        serializersModule = libSerializer
    }

    fun serialization(list: List<ColoredShape2d>): String {
        return json.encodeToString(list)
    }

    fun deserialization(data: String): List<ColoredShape2d> {
        return json.decodeFromString(data)
    }

}