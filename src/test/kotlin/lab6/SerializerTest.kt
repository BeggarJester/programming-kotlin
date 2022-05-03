package lab6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertFailsWith

internal class SerializerTest {

    @Test
    fun `test objects serialization and write into file`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Square(colour2, colour1, 5.0)
        val shape2 = Circle(colour1, colour1, 1.0)
        val mySerializer = Serializer()
        val list = mutableListOf(shape1, shape2)
        mySerializer.serialization(list, "output")
        assertEquals(
            "[\n" +
                    "    {\n" +
                    "        \"type\": \"lab6.Square\",\n" +
                    "        \"borderColor\": {\n" +
                    "            \"red\": 1,\n" +
                    "            \"green\": 1,\n" +
                    "            \"blue\": 1,\n" +
                    "            \"alpha\": 0\n" +
                    "        },\n" +
                    "        \"fillColor\": {\n" +
                    "            \"red\": 255,\n" +
                    "            \"green\": 255,\n" +
                    "            \"blue\": 255,\n" +
                    "            \"alpha\": 0\n" +
                    "        },\n" +
                    "        \"side\": 5.0\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"type\": \"lab6.Circle\",\n" +
                    "        \"borderColor\": {\n" +
                    "            \"red\": 255,\n" +
                    "            \"green\": 255,\n" +
                    "            \"blue\": 255,\n" +
                    "            \"alpha\": 0\n" +
                    "        },\n" +
                    "        \"fillColor\": {\n" +
                    "            \"red\": 255,\n" +
                    "            \"green\": 255,\n" +
                    "            \"blue\": 255,\n" +
                    "            \"alpha\": 0\n" +
                    "        },\n" +
                    "        \"radius\": 1.0\n" +
                    "    }\n" +
                    "]",
            File("src/main/kotlin/lab6/output.txt").readText()
        )
    }

    @Test
    fun `test read string from file and its deserialization into objects`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Square(colour2, colour1, 5.0)
        val shape2 = Circle(colour1, colour1, 1.0)
        val mySerializer = Serializer()
        mySerializer.serialization(mutableListOf(shape1, shape2), "output")
        assertEquals(mutableListOf(shape1, shape2), mySerializer.deserialization("output"))
    }

    @Test
    fun `test read from non-existing file`() {
        val mySerializer = Serializer()
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { mySerializer.deserialization("nameFile") }
        )
        assertEquals(exception.message, "File doesn't exist")

    }
}