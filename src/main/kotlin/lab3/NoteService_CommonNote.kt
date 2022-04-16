package lab3

import java.net.URL
import java.time.LocalDate

interface NoteService {
    fun getAllNotes(): List<Note>
    fun getAllTextNotes(): List<Note.TextNote>
    fun getAllTasks(): List<Note.Task>
    fun getAllLinks(): List<Note.Link>
    fun createTextNote(title: String, content: String): Note.TextNote
    fun createTaskNote(title: String, description: String, deadline: LocalDate): Note.Task
    fun createLinkNote(title: String, url: URL): Note.Link
    fun removeNote(title: String, typeName: String)
    fun find(title: String, typeName: String): List<Note>
    fun getSortedByTitle(): List<Note>
    fun getSortedByDate(): List<Note>
}

interface CommonNote {
    val title: String
    val date: LocalDate
}