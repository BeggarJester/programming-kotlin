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

fun main() {

    // original string contains information about all books
    val superString = """1. The Lord of the Rings // JRR Tolkien // 1968
        2. Catch-22 // Joseph Heller // 1961
        3. The Godfather // Mario Puzo // 1969
        4. The Picture of Dorian Gray // Oscar Wilde // 1890
        5. Advanced math for Lawyers // Andrey Kolpakov, Augustus De Morgan // 1969 """
    val library: List<Book> = parseBooks(superString)


}