package lab1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class Test {

    @Test
    fun `test one correct string parse` () {
        val input = "1. The Lord of the Rings // JRR Tolkien // 1968"
        assertEquals("The Lord of the Rings", parseBooks(input)[0].title)
        assertEquals("JRR Tolkien", parseBooks(input)[0].authors[0])
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
            "The Picture of Dorian Gray // Oscar Wilde // 1890",
            getList(findOldestBookYear(parseBooks(input)))
        )
        assertEquals(
            "The Godfather // Mario Puzo // 1969\nAdvanced math for Lawyers // Andrey Kolpakov, Augustus De Morgan // 1969",
            getList(findLatestBookYear(parseBooks(input)))
        )
        assertEquals(
            "The Picture of Dorian Gray // Oscar Wilde // 1890",
            getList(findLongestBookTitle(parseBooks(input)))
        )
        assertEquals("Catch-22 // Joseph Heller // 1961", getList(findShortestBookTitle(parseBooks(input))))
    }

    @Test
    fun `test algorithms of finding the oldest, the latest, the longest and the shortest books from empty string exception`() {
        val input = ""
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { getList(findOldestBookYear(parseBooks(input))) }
        )
        assertEquals(exception.message, "The string is empty")
        val exception2 = assertFailsWith<IllegalArgumentException>(
            block = { getList(findLatestBookYear(parseBooks(input))) }
        )
        assertEquals(exception2.message, "The string is empty")
        val exception3 = assertFailsWith<IllegalArgumentException>(
            block = { getList(findLongestBookTitle(parseBooks(input))) }
        )
        assertEquals(exception3.message, "The string is empty")
        val exception4 = assertFailsWith<IllegalArgumentException>(
            block = { getList(findShortestBookTitle(parseBooks(input))) }
        )
        assertEquals(exception4.message, "The string is empty")
    }

}