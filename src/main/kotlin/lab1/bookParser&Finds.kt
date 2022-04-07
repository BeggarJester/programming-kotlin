package lab1

// parse input string to list of Books
fun parseBooks(books: String): List<Book> {
    val listBooks: MutableList<Book> = mutableListOf()
    // if string is empty
    if (books.isEmpty()) {
        return listBooks
    }
    val originalString: String = books
    val markNewBook = "\n"
    // parse input string to common books strings
    val stringToBooks = originalString.split(markNewBook)
    // go through list of books strings
    for (bookIndex in stringToBooks) {
        var oneStringBook = bookIndex
        val markPoint = '.'
        oneStringBook = oneStringBook.substringAfter(markPoint).trim() // drop the book original number from string
        val markParse = "//"
        val bookTitle = oneStringBook.substringBefore(markParse).trim() // get the book title
        if (bookTitle.isEmpty()) {
            throw IllegalArgumentException("One of the books is missing a title")
        }
        oneStringBook = oneStringBook.substringAfter(markParse).trim() // drop the book title from string
        val stringToAuthors = oneStringBook.substringBefore(markParse).trim() // get the book authors
        val markComma = ','
        val bookAuthors = stringToAuthors.split(markComma) // parse book authors to list of book authors
        if (bookAuthors.none { it != "" }) {
            throw IllegalArgumentException("One of the books is missing at least one author")
        }
        oneStringBook = oneStringBook.substringAfter(markParse).trim() // drop the book authors from string
        if (oneStringBook.isEmpty()) {
            throw IllegalArgumentException("One of the books is missing the year of publication")
        }
        val bookYear = oneStringBook.toInt() // get the book publication year
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
        return listOf()
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
        return listOf()
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
        return listOf()
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
        return listOf()
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
    var str = ""
    for (book in library) {
        str += book.title + " // " + getAuthors(book.authors) + " // " + book.year + "\n"
    }
    // drop the final new string mark
    return str.dropLast(1)
}