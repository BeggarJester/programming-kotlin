package lab3

import java.net.URL
import java.time.LocalDate
import java.util.logging.Logger

class ProcessNote : NoteService {
    private val logger = Logger.getLogger(this.javaClass.name)

    // list for all notes storage
    private val noteList: MutableList<Note> = mutableListOf()

    // add new text note to the list
    fun newNote(title: String, content: String) {
        noteList.add(createTextNote(title, content))
        logger.info("new text note added to NoteService")
    }

    // add new task note to the list
    fun newNote(title: String, description: String, deadline: LocalDate) {
        noteList.add(createTaskNote(title, description, deadline))
        logger.info("new task note added to NoteService")
    }

    // add new link note to the list
    fun newNote(title: String, url: URL) {
        noteList.add(createLinkNote(title, url))
        logger.info("new link note added to NoteService")
    }

    // return list with all notes
    override fun getAllNotes(): List<Note> {
        logger.info("returned all notes from NoteService")
        return noteList
    }

    // return list with all text notes
    override fun getAllTextNotes(): List<Note.TextNote> {
        logger.info("returned all text notes from NoteService")
        return noteList.filterIsInstance<Note.TextNote>()
    }

    // return list with all task notes
    override fun getAllTasks(): List<Note.Task> {
        logger.info("returned all task notes from NoteService")
        return noteList.filterIsInstance<Note.Task>()
    }

    // return list with all link notes
    override fun getAllLinks(): List<Note.Link> {
        logger.info("returned all links notes from NoteService")
        return noteList.filterIsInstance<Note.Link>()
    }

    // create text note
    override fun createTextNote(title: String, content: String): Note.TextNote {
        logger.info("created new text note")
        return Note.TextNote(title, LocalDate.now(), content)
    }

    // create task note
    override fun createTaskNote(title: String, description: String, deadline: LocalDate): Note.Task {
        logger.info("created new task note")
        return Note.Task(title, LocalDate.now(), description, deadline)
    }

    // create link note
    override fun createLinkNote(title: String, url: URL): Note.Link {
        logger.info("created new link note")
        return Note.Link(title, LocalDate.now(), url)
    }

    // remove note with user title & type
    override fun removeNote(title: String, typeName: String) {
        noteList.removeIf { it.title == title && it::class.simpleName == typeName }
        logger.info("removed some notes from NoteService")
    }

    // find note by user title & type
    override fun find(title: String, typeName: String): List<Note> {
        logger.info("returned list of searched elements of NoteService")
        return noteList.filter { it.title == title && it::class.simpleName == typeName }
    }

    // get list contains notes sorted by title in ascending
    override fun getSortedByTitle(): List<Note> {
        logger.info("returned list of sorted by title NoteService elements")
        return noteList.sortedBy { it.title }
    }

    // get list contains notes sorted by date in ascending
    override fun getSortedByDate(): List<Note> {
        logger.info("returned list of sorted by date NoteService elements")
        return noteList.sortedBy { it.date }
    }

}