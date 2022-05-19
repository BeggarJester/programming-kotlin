package lab6

import lab2.*

fun main() {
    val colour1 = Color(255, 255, 255, 0)
    val colour2 = Color(1, 1, 1, 0)
    println("Create new Serializer")
    val file = FileManager("src/main/kotlin/lab6/input.txt", "src/main/kotlin/lab6/output.txt")
    val mySerializer = SerializerManager()
    println("\nRead objects list from input file:")
    val temporaryList = mySerializer.deserialization(file.read()).toMutableList()
    println(temporaryList)
    println("\nAdd some items to list:")
    temporaryList.add(Circle(colour1, colour2, 7.5))
    temporaryList.add(Square(colour1, colour2, 7.5))
    temporaryList.add(Rectangle(colour1, colour2, 7.5, 5.7))
    temporaryList.add(Triangle(colour1, colour2, 3.0, 4.0, 5.0))
    println(temporaryList)
    println("\nWrite objects into output file after serialization")
    file.write(mySerializer.serialization(temporaryList))
}