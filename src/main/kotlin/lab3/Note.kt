package lab3

import java.net.URL
import java.time.LocalDate

sealed class Note : CommonNote {

    class TextNote(
        override val title: String,
        override val date: LocalDate,
        content: String
    ) : Note() {
        private val content: String

        init {
            this.content = content
        }

        override fun toString(): String {
            return "TextNote(title='$title', date=$date, content='$content')"
        }
    }

    class Task(
        override val title: String,
        override val date: LocalDate,
        description: String,
        data: LocalDate,
    ) : Note() {
        private val description: String
        private val deadline: LocalDate

        init {
            this.description = description
            deadline = data
        }

        override fun toString(): String {
            return "Task(title='$title', date=$date, description='$description', deadline=$deadline)"
        }
    }

    class Link(
        override val title: String,
        override val date: LocalDate,
        url: URL
    ) : Note() {
        private val url: URL

        init {
            this.url = url
        }

        override fun toString(): String {
            return "Link(title='$title', date=$date, url=$url)"
        }
    }
}
