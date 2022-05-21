package coursework

import coursework.view.GameUI
import javax.swing.SwingUtilities

fun main() {

    SwingUtilities.invokeLater {
        val game = GameUI()
        game.isVisible = true
    }
}