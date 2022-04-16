package lab3

import java.net.URL
import java.time.LocalDate

fun main() {

    println("Create empty myNoteProcessor, add some noted and print them all:")
    val myNoteProcessor = ProcessNote()
    myNoteProcessor.newNote("Plan for the day", "Programming")
    myNoteProcessor.newNote("Plan for the tomorrow day", "Programming too")
    myNoteProcessor.newNote("Laboratory work №3", "Add comments to methods", LocalDate.parse("2022-04-14"))
    myNoteProcessor.newNote("Laboratory work №3", "Test the program", LocalDate.parse("2018-12-15"))
    myNoteProcessor.newNote("University website", URL("https://etu.ru/"))
    myNoteProcessor.newNote("Student's personal account", URL("https://lk.etu.ru"))
    println(myNoteProcessor.getAllNotes())
    println("Get all text notes from collection:")
    println(myNoteProcessor.getAllTextNotes())
    println("Get all task notes from collection:")
    println(myNoteProcessor.getAllTasks())
    println("Get all link notes from collection:")
    println(myNoteProcessor.getAllLinks())
    println("Remove notes from collection by title and type:")
    myNoteProcessor.removeNote("Plan for the day", "TextNote")
    println(myNoteProcessor.getAllNotes())
    println("Find notes in collection by title and type:")
    println(myNoteProcessor.find("Laboratory work №3", "Task"))
    println("Print all notes notes sorted by title in ascending:")
    println(myNoteProcessor.getSortedByTitle())
    println("Print all notes notes sorted by date in ascending:")
    println(myNoteProcessor.getSortedByDate())

}