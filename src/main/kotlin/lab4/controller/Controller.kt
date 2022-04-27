package lab4.controller

import lab4.model.Model
import lab4.model.State

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        while (model.state == State.MOVE) {
            val input = readln()
            try {
                model.doMove(input.lowercase())
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
