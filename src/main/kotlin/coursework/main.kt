package coursework

import javax.swing.SwingUtilities

fun main() {

    SwingUtilities.invokeLater {
        val game = Game()
        game.isVisible = true
    }
}