package lab4.viewer

import lab4.model.Model
import lab4.model.ModelChangeListener
import lab4.model.State
import java.awt.BorderLayout
import java.awt.Component
import javax.swing.*

class MazeUi : JFrame("Maze"), ModelChangeListener {

    private var gameModel: Model = Model()
    private val statusLabel = JLabel("Status", JLabel.CENTER)
    private val buttons = mutableListOf<MutableList<JButton>>()

    init {
        setSize(500, 500)
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null) // create window on screen center
        val icon = ImageIcon("src/main/kotlin/lab4/labyrinth.png")
        iconImage = icon.image // custom app icon
        updateFont(statusLabel, 20.0f)
        rootPane.contentPane = JPanel(BorderLayout()).apply {
            add(statusLabel, BorderLayout.NORTH)
            add(createBoardPanel(), BorderLayout.CENTER)
            add(createRestartButton(), BorderLayout.SOUTH)
            border = BorderFactory.createEmptyBorder()
        }

        resubscribe()
    }

    private fun createRestartButton(): Component {
        val restartButton = JButton("Restart")
        updateFont(restartButton, 20.0f)
        restartButton.addActionListener {
            if (gameModel.state == State.MOVE) {
                val dialogOption = JOptionPane.showConfirmDialog(
                    this,
                    "Game not finished, are you sure?",
                    "Restart",
                    JOptionPane.OK_CANCEL_OPTION
                )
                if (dialogOption == JOptionPane.OK_OPTION) {
                    resubscribe()
                }
            } else {
                resubscribe()
            }
        }

        return restartButton
    }

    private fun resubscribe() {
        gameModel.removeModelChangeListener(this)
        gameModel = Model()
        gameModel.addModelChangeListener(this)
        updateGameUI()
    }

    private fun createBoardPanel(): Component {

    }


    private fun updateFont(component: JComponent, newFontSize: Float) {
        val font = component.font
        val derivedFont = font.deriveFont(newFontSize)
        component.font = derivedFont
    }

    override fun onModelChanged() {
        updateGameUI()
    }

    private fun updateGameUI() {

    }

}

