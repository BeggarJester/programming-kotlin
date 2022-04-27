package lab4.model

import lab4.model.Cell.*
import java.io.File

// enum class for cell information storage
enum class Cell(private val textValue: String) {
    WALL("#"),
    PLAYER("P"),
    FREE_WAY("-"),
    EXIT("E");

    override fun toString(): String = textValue
}

// maze solving state
enum class State(val textValue: String) {
    MOVE("Waiting for move..."),
    FINISH("Maze solved!")
}

interface ModelChangeListener {
    fun onModelChanged()
}

// maze model class
class Model {
    private var player = Pair(0, 0)
    var boardRow = 0
    var boardColumn = 0
    val board: MutableList<MutableList<Cell>> = initStartBoard()

    var state: State = State.MOVE
        private set

    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: ModelChangeListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach { it.onModelChanged() }
    }



    // read maze map from file
    private fun initStartBoard(): MutableList<MutableList<Cell>> {
        val mazeMap = File("src/main/kotlin/lab4/maze.txt").readLines()
        boardRow = mazeMap.size
        boardColumn = mazeMap[0].length
        val startBoard = MutableList(boardRow) {
            MutableList(boardColumn) { WALL }
        }
        for (i in 0 until boardRow) {
            for (j in 0 until boardColumn) {
                when (mazeMap[i][j]) {
                    'P' -> {
                        startBoard[i][j] = PLAYER
                        player = Pair(i, j)
                    }
                    '-' -> startBoard[i][j] = FREE_WAY
                    'E' -> startBoard[i][j] = EXIT
                    else -> WALL
                }
            }
        }
        return startBoard
    }

    override fun toString(): String {
        return buildString {
            append(state).appendLine()

            board.forEach {
                append(it).appendLine()
            }
        }
    }

}
