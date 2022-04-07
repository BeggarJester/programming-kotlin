package lab1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class Test {

    @Test
    fun `test one correct string parse`() {
        val input = "1. The Lord of the Rings // JRR Tolkien // 1968"
        assertEquals(
            listOf(Book("The Lord of the Rings", listOf("JRR Tolkien"), 1968)),
            parseBooks(input)
        )
    }

    @Test
    fun `test empty string parse`() {
        val input = ""
        assertEquals(
            true,
            parseBooks(input).isEmpty()
        )
    }

    @Test
    fun `test one missing title string parse exception`() {
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

    @Test
    fun `test one missing year string parse exception`() {
        val input = "1. The Lord of the Rings // JRR Tolkien // "
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { parseBooks(input) }
        )
        assertEquals(exception.message, "One of the books is missing the year of publication")
    }

    @Test
    fun `test one negative year string parse exception`() {
        val input = "1. The Lord of the Rings // JRR Tolkien // -200"
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { parseBooks(input) }
        )
        assertEquals(exception.message, "The year of publication of one of the books is not correct")
    }

    @Test
    fun `test algorithms of finding the oldest, the latest, the longest and the shortest books from correct string`() {
        val input = """1. The Lord of the Rings // JRR Tolkien // 1968
        2. Catch-22 // Joseph Heller // 1961
        3. The Godfather // Mario Puzo // 1969
        4. The Picture of Dorian Gray // Oscar Wilde // 1890
        5. Advanced math for Lawyers // Andrey Kolpakov, Augustus De Morgan // 1969 """
        assertEquals(
            listOf(Book("The Picture of Dorian Gray", listOf("Oscar Wilde"), 1890)),
            findOldestBookYear(parseBooks(input))
        )
        assertEquals(
            listOf(
                Book("The Godfather", listOf("Mario Puzo"), 1969),
                Book("Advanced math for Lawyers", listOf("Andrey Kolpakov", "Augustus De Morgan"), 1969)
            ),
            findLatestBookYear(parseBooks(input))
        )
        assertEquals(
            listOf(Book("The Picture of Dorian Gray", listOf("Oscar Wilde"), 1890)),
            findLongestBookTitle(parseBooks(input))
        )
        assertEquals(
            listOf(Book("Catch-22", listOf("Joseph Heller"), 1961)),
            findShortestBookTitle(parseBooks(input))
        )
    }

    @Test
    fun `test algorithms of finding the oldest, the latest, the longest and the shortest books from empty string`() {
        val input = ""
        assertEquals(
            true,
            findOldestBookYear(parseBooks(input)).isEmpty()
        )
        assertEquals(
            true,
            findLatestBookYear(parseBooks(input)).isEmpty()
        )
        assertEquals(
            true,
            findLongestBookTitle(parseBooks(input)).isEmpty()
        )
        assertEquals(
            true,
            findShortestBookTitle(parseBooks(input)).isEmpty()
        )
    }

}