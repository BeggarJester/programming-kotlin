package lab1

fun main() {

    // original string contains information about all books
    val superString = """1. The Lord of the Rings // JRR Tolkien // 1968
        2. Catch-22 // Joseph Heller // 1961
        3. The Godfather // Mario Puzo // 1969
        4. The Picture of Dorian Gray // Oscar Wilde // 1890
        5. Advanced math for Lawyers // Andrey Kolpakov, Augustus De Morgan // 1969 """
    val library: List<Book> = parseBooks(superString)
    println("Print the original books list before parse:\n")
    println(superString)
    println("\nPrint the original books list:\n")
    println(getList(library))
    println("\nPrint the oldest books list:")
    println(getList(findOldestBookYear(library)))
    println("\nPrint the latest books list:")
    println(getList(findLatestBookYear(library)))
    println("\nPrint the longest titled books list:")
    println(getList(findLongestBookTitle(library)))
    println("\nPrint the shortest titled books list:")
    println(getList(findShortestBookTitle(library)))
}