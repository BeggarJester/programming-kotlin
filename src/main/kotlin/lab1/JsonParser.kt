package lab1

data class JsonParser(
    val batchcomplete: String,
    val `continue`: Continue,
    val query: Query
)