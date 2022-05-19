package lab4.controller

import lab4.model.Model
import lab4.model.State
import lab4.model.Direction.*

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        while (model.state == State.MOVE) {
            val input = readln()
            try {
                when (input.lowercase()) {
                    UP.toString() -> model.doMove(UP)
                    DOWN.toString() -> model.doMove(DOWN)
                    LEFT.toString() -> model.doMove(LEFT)
                    RIGHT.toString() -> model.doMove(RIGHT)
                    ENDGAME.toString() -> model.doMove(ENDGAME)
                }

            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
