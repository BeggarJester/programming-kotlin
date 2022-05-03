package lab6

fun main() {

    val colour1 = Color(255, 255, 255, 0)
    println("Create new Serializer")
    val mySerializer = Serializer()
    println("\nRead objects list from input file:")
    val temporaryList = mySerializer.deserialization("input")
    println(temporaryList)
    println("\nAdd some items to list:")
    temporaryList.add(Circle(colour1, colour1, 7.5))
    temporaryList.add(Square(colour1, colour1, 7.5))
    temporaryList.add(Rectangle(colour1, colour1, 7.5, 5.7))
    temporaryList.add(Triangle(colour1, colour1, 3.0, 4.0, 5.0))
    println(temporaryList)
    println("\nWrite objects into output file after serialization")
    mySerializer.serialization(temporaryList, "output")
}