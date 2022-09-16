package lab1

import com.google.gson.Gson
import java.awt.Desktop
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.net.URLEncoder

class WikipediaSearcher {
    private var list: ArrayList<Search> = arrayListOf()

    fun search() {
        var phrase: String
        do {
            println("Enter phrase to Wikipedia search:")
            phrase = URLEncoder.encode(readLine().toString(), "UTF-8")
        } while(phrase == "")
        val link = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=$phrase"
        val connection = URL(link).openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        list = Gson().fromJson(data, JsonParser::class.java).query.search
    }

    private fun availableArticle() {
        println("Available articles for searched phrase:")
        for (indexArticles in 0 until list.size) {
            println("${indexArticles + 1}: ${list[indexArticles].title}")
        }
    }

    private fun isInteger(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun browse() {
        if (list.isEmpty()) search()
        availableArticle()
        var pageIndexString: String
        var pageIndex: Int
        val desk: Desktop = Desktop.getDesktop()
        do {
            println("Choose article index to browse it:")
            pageIndexString = readLine().toString()
            while(!isInteger(pageIndexString)) {
                println("Choose article index to browse it:")
                pageIndexString = readLine().toString()
            }
            pageIndex = pageIndexString.toInt()
        } while (pageIndex !in 1 .. list.size)
        desk.browse(URI("https://ru.wikipedia.org/w/index.php?curid=${list[(pageIndex - 1)].pageid}"))
    }
}