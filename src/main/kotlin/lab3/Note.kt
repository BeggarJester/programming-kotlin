package lab3

import java.net.URL
import java.time.LocalDate

sealed class Note(val title: String, val date: LocalDate) {

    class TextNote(
        title: String,
        date: LocalDate,
        private val content: String
    ) : Note(title, date) {

        override fun toString(): String {
            return "TextNote(title='$title', date=$date, content='$content')"
        }
    }

    class Task(
        title: String,
        date: LocalDate,
        private val description: String,
        private val deadline: LocalDate,
    ) : Note(title, date) {

        override fun toString(): String {
            return "Task(title='$title', date=$date, description='$description', deadline=$deadline)"
        }
    }

    class Link(
        title: String,
        date: LocalDate,
        private val url: URL
    ) : Note(title, date) {

        override fun toString(): String {
            return "Link(title='$title', date=$date, url=$url)"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (title != other.title) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}
