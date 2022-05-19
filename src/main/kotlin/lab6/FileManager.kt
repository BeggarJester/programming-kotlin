package lab6

import java.io.File

// class FileManager for read and write txt file
class FileManager(private val pathInput: String, private val pathOutput: String) {

    constructor(pathInputOutput: String) : this(pathInputOutput, pathInputOutput)

    fun write(data: String) {
        File(pathOutput).writeText(data)
    }

    fun read(): String {
        if (!File(pathInput).exists())
            throw IllegalArgumentException("File doesn't exist")
        return File(pathInput).readText()
    }

}