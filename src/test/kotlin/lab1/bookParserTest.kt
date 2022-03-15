package lab1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class Test {

    @Test
    fun `test one correct string parse` () {
        val input = "1. The Lord of the Rings // JRR Tolkien // 1968"
        assertEquals("The Lord of the Rings", parseBooks(input)[0].title)
        assertEquals("JRR Tolkien", parseBooks(input)[0].authors)
        assertEquals(1968, parseBooks(input)[0].year)
    }

    @Test
    fun `test empty string parse exception` () {
        val input = ""
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { parseBooks(input) }
        )
        assertEquals(exception.message, "The string is empty")
    }

    @Test
    fun `test one missing title string parse exception` () {
        val input = "1.// JRR Tolkien // 1968"
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { parseBooks(input) }
        )
        assertEquals(exception.message, "One of the books is missing a title")
    }

    @Test
    fun `test one missing author string parse exception`() {
        val input = "1. The Lord of the Rings // // 1968"
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { parseBooks(input) }
        )
        assertEquals(exception.message, "One of the books is missing at least one author")
    }
}
