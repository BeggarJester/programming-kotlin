package lab2

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.FileReader

// class for read, parse and processing XML & CSV files
class FileReader {

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