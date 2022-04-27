package lab4.swingMain

import lab4.viewer.MazeUi
import javax.swing.SwingUtilities

fun main() {

    SwingUtilities.invokeLater {
        val mazeUi = MazeUi()
        mazeUi.isVisible = true
    }

}