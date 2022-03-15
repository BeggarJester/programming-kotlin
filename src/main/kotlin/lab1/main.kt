package lab1

// parse input string to list of Books
fun parseBooks(books: String): List<Book> {
    // exception if string is empty
    if (books.isEmpty()) {
        throw IllegalArgumentException("The string is empty")
    }
    // advertisement section
    var bookTitle: String
    var stringToAuthors: String
    var bookAuthors: List<String>
    val stringToBooks: List<String>
    var bookYear: Int
    val originalString: String = books
    val markNewBook = "\n"
    val markPoint = '.'
    val markComma = ','
    val markParse = "//"
    var oneStringBook: String
    val listBooks: MutableList<Book> = mutableListOf()
    // parse input string to common books strings
    stringToBooks = originalString.split(markNewBook)
    // go through list of books strings
    for (bookIndex in stringToBooks) {
        oneStringBook = bookIndex
        oneStringBook = oneStringBook.substringAfter(markPoint).trim() // drop the book original number from string
        bookTitle = oneStringBook.substringBefore(markParse).trim() // get the book title
        if (bookTitle.isEmpty()) {
            throw IllegalArgumentException("One of the books is missing a title")
        }
        oneStringBook = oneStringBook.substringAfter(markParse).trim() // drop the book title from string
        stringToAuthors = oneStringBook.substringBefore(markParse).trim() // get the book authors
        bookAuthors = stringToAuthors.split(markComma) // parse book authors to list of book authors
        if (bookAuthors.none { it != "" }) {
            throw IllegalArgumentException("One of the books is missing at least one author")
        }
        oneStringBook = oneStringBook.substringAfter(markParse).trim() // drop the book authors from string
        if (oneStringBook.isEmpty()) {
            throw IllegalArgumentException("One of the books is missing the year of publication")
        }
        bookYear = oneStringBook.toInt() // get the book publication year
        if (bookYear < 1) {
            throw IllegalArgumentException("The year of publication of one of the books is not correct")
        }
        val book = Book(bookTitle, authorsTrim(bookAuthors), bookYear) // create object of Book
        listBooks.add(book) //  and add to the list
    }
    return listBooks // return the Books list
}

// leading and trailing whitespace removed for each author from list
private fun authorsTrim(authors: List<String>): List<String> {
    val authorsTrimed: MutableList<String> = mutableListOf()
    for (author in authors) {
        authorsTrimed.add(author.trim())
    }
    return authorsTrimed
}

// get the list of the oldest books
fun findOldestBookYear(library: List<Book>): List<Book> {
    if (library.isEmpty()) {
        throw IllegalArgumentException("The string is empty")
    }
    var oldestBookYear = Int.MAX_VALUE
    // get the oldest books year
    for (book in library) {
        if (book.year < oldestBookYear) oldestBookYear = book.year
    }

    // return list of books with the oldest publication year
    return library.filter { it.year == oldestBookYear }
}

//  get the list of the latest books
fun findLatestBookYear(library: List<Book>): List<Book> {
    if (library.isEmpty()) {
        throw IllegalArgumentException("The string is empty")
    }
    var latestBookYear = Int.MIN_VALUE
    // get the latest books year
    for (book in library) {
        if (book.year > latestBookYear) latestBookYear = book.year
    }

    // return list of books with the latest publication year
    return library.filter { it.year == latestBookYear }
}

//  get the list of the longest titled books
fun findLongestBookTitle(library: List<Book>): List<Book> {
    if (library.isEmpty()) {
        throw IllegalArgumentException("The string is empty")
    }
    var longestBookTitle = Int.MIN_VALUE
    // get the longest book title length
    for (book in library) {
        if (book.title.length > longestBookTitle) longestBookTitle = book.title.length
    }

    // return list of the longest titled books
    return library.filter { it.title.length == longestBookTitle }
}

//  get the list of the shortest titled books
fun findShortestBookTitle(library: List<Book>): List<Book> {
    if (library.isEmpty()) {
        throw IllegalArgumentException("The string is empty")
    }
    var shortestBookTitle = Int.MAX_VALUE
    // get the shortest book title length
    for (book in library) {
        if (book.title.length < shortestBookTitle) shortestBookTitle = book.title.length
    }

    // return list of the shortest titled books
    return library.filter { it.title.length == shortestBookTitle }
}

// return the string of book authors
private fun getAuthors(authors: List<String>): String {
    var listAuthor = ""
    for (author in authors) {
        listAuthor += author
        listAuthor += ", "
    }
    // drop the final comma and whitespace
    listAuthor = listAuthor.dropLast(2)
    return listAuthor
}

// get the string of book information
fun getList(library: List<Book>): String {
    var str= ""
    for (book in library) {
        str += book.title + " // " + getAuthors(book.authors) + " // " + book.year + "\n"
    }
    // drop the final new string mark
    return str.dropLast(1)
}

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