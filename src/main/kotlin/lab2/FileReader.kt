package lab2

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.w3c.dom.Document
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.xml.parsers.DocumentBuilderFactory

// class for read, parse and processing XML & CSV files
class FileReader {

    // read XML file and compute statistics about it and its processing
    fun readXML(filePath: String) {
        val startTime = System.currentTimeMillis()
        val hashMap: HashMap<Address, Int> = HashMap()
        val citiesNamesList = mutableListOf<String>()

        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document: Document = builder.parse(File(filePath))
        val addressElements = document.documentElement.getElementsByTagName("item")
        for (indexAddress in 0 until addressElements.length) {
            val address = addressElements.item(indexAddress)
            val attributes = address.attributes
            val newAddress = Address(
                attributes.getNamedItem("city").nodeValue,
                attributes.getNamedItem("street").nodeValue,
                attributes.getNamedItem("house").nodeValue.toInt(),
                attributes.getNamedItem("floor").nodeValue.toInt()
            )
            fillHashMapAndList(citiesNamesList, newAddress, hashMap)
        }

        citiesNamesList.sort()
        printStatistics(hashMap, citiesNamesList)
        val runTime = System.currentTimeMillis() - startTime
        println("\nRuntime is $runTime milliseconds\n")
    }

    // read CSV file and compute statistics about it and its processing
    fun readCSV(filePath: String) {
        val startTime = System.currentTimeMillis()
        val hashMap: HashMap<Address, Int> = HashMap()
        val citiesNamesList = mutableListOf<String>()

        val bufferedReader = BufferedReader(FileReader(filePath))
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT
            .withDelimiter(';')
            .withQuote('"')
            .withRecordSeparator("\r\n")
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim()
        )
        for (csvRecord in csvParser) {
            val newAddress = Address(
                csvRecord.get("city"),
                csvRecord.get("street"),
                csvRecord.get("house").toInt(),
                csvRecord.get("floor").toInt()
            )
            fillHashMapAndList(citiesNamesList, newAddress, hashMap)
        }

        citiesNamesList.sort()
        printStatistics(hashMap, citiesNamesList)
        val runTime = System.currentTimeMillis() - startTime
        println("\nRuntime is $runTime milliseconds\n")
    }

    // compute and output statistics about file and its processing
    private fun printStatistics(hashMap: HashMap<Address, Int>, citiesNamesList: MutableList<String>) {
        val doubles = hashMap.filterValues { it > 1 }
        for (entry in doubles) {
            println("There are ${entry.value} entries about ${entry.key.city} city in file")
        }
        println()
        for (entry in citiesNamesList) {
            val count = hashMap.filterKeys { entry == it.city }
            for (i in 1..5) {
                println("$entry city has ${count.filterKeys { i == it.floor }.count()} houses with $i floors")
            }
        }
    }

    // fill hashmap & names list by items from file
    private fun fillHashMapAndList(citiesNamesList: MutableList<String>, newAddress: Address, hashMap: HashMap<Address, Int>) {
        if (!citiesNamesList.contains(newAddress.city)) citiesNamesList.add(newAddress.city)
        if (hashMap.containsKey(newAddress)) {
            val item: Int? = hashMap[newAddress]
            if (item != null) {
                hashMap[newAddress] = item + 1
            }
        } else {
            hashMap[newAddress] = 1
        }
    }
}