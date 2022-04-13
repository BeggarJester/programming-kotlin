package lab3

interface NoteService: Note {
    fun getAllNotes(): List<Note>
    fun getAllTextNotes(): List<Note.TextNote>
    fun getAllTasks(): List<Note.Task>
    fun getAllLinks(): List<Note.Link>

    fun createTextNote(title: String, content: String): Note.TextNote
}

interface Note{
    val title: String
    var date : java.time.LocalDate
}

// class Circle(override val borderColor: Color, override val fillColor: Color, radius: Double) : ColoredShape2d

class TextNote(override val title: String, override var date : java.time.LocalDate, text: String): Note{
    private val content: String
    init{
        content = text
    }

}

fun main(){
    println("hello")
}