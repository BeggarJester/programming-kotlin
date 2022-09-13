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



}