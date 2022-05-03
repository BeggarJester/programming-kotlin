package lab6

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Serializer {

    private val json = Json {
        prettyPrint = true
        serializersModule = libSerializer
    }

    // objects serialization and write into file
    fun serialization(list: MutableList<ColoredShape2d>, title: String) {
        File("src/main/kotlin/lab6/$title.txt").writeText(json.encodeToString(list))
    }

    // read string from file and its deserialization into objects
    fun deserialization(title: String): MutableList<ColoredShape2d> {
        if (!File("src/main/kotlin/lab6/$title.txt").exists())
            throw IllegalArgumentException("File doesn't exist")
        return json.decodeFromString(File("src/main/kotlin/lab6/$title.txt").readText())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Serializer

        if (json != other.json) return false

        return true
    }

    override fun hashCode(): Int {
        return json.hashCode()
    }
}