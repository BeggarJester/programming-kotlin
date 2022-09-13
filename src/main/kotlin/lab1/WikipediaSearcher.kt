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
        println("Enter phrase to Wikipedia search:")
        val phrase = URLEncoder.encode(readLine().toString(), "UTF-8")
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

    fun browse() {
        if (list.isEmpty()) search()
        availableArticle()
        println("Choose article index to browse it:")
        val desk: Desktop = Desktop.getDesktop()
        val pageIndex = readLine()?.toInt()
        if (pageIndex != null) {
            desk.browse(URI("https://ru.wikipedia.org/w/index.php?curid=${list[(pageIndex - 1)].pageid}"))
        }
    }
}