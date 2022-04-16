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

    }

    // create task note
    override fun createTaskNote(title: String, description: String, deadline: LocalDate): Note.Task {

    }

    // create link note
    override fun createLinkNote(title: String, url: URL): Note.Link {

    }

    // remove note with user title & type
    override fun removeNote(title: String, typeName: String) {

    }

    // find note by user title & type
    override fun find(title: String, typeName: String): List<Note> {

    }

    // get list contains notes sorted by title in ascending
    override fun getSortedByTitle(): List<Note> {

    }

    // get list contains notes sorted by date in ascending
    override fun getSortedByDate(): List<Note> {

    }

}