package lab4.consoleMain

import lab4.controller.Controller
import lab4.model.Model
import lab4.viewer.ConsoleUi

fun main() {

    val maze = Model()
    ConsoleUi(maze)
    Controller(maze)

}