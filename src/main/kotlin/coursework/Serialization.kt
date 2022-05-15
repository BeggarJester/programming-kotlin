package coursework

import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Serializer1 {

    private val json = Json {
        prettyPrint = true
    }

    // objects serialization and write into file
    fun serialization(list: MutableList<Pair<String, Int>>) {
        File("src/main/kotlin/coursework/leaders.txt").writeText(json.encodeToString(list))
    }

    // read string from file and its deserialization into objects
    fun deserialization(): MutableList<Pair<String, Int>> {
        if (!File("src/main/kotlin/coursework/leaders.txt").exists())
            throw IllegalArgumentException("File doesn't exist")
        return json.decodeFromString(File("src/main/kotlin/coursework/leaders.txt").readText())
    }
}

