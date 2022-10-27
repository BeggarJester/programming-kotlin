package lab2

import java.io.File

fun main() {

    var exit = false
    while (!exit) {
        print("Program instruction:\nEnter the path to the XML or CSV file to process it.\nTo complete the work, enter  'exit' or 'quit' command. \nYour enter: ")
        val nameFile = readLine()
        val reader = FileReader()
        when(nameFile) {
            "exit" -> {
                exit = true
            }
            "quit" -> {
                exit = true
            }
            else -> {
                if(nameFile != null && File(nameFile).exists()) {
                    if (nameFile.lowercase().endsWith("csv")) {
                        println("\nCSV file processing...\n")
                        reader.readCSV(nameFile)
                    } else if (nameFile.lowercase().endsWith("xml")) {
                        println("\nXML file processing...\n")
                        reader.readXML(nameFile)
                    } else println("\nUnsupported format. Try again...\n")
                } else {
                    println("\nInvalid file path. Try again...\n")
                }
            }
        }
    }
}

