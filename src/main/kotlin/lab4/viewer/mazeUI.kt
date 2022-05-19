package lab4.viewer

import lab4.model.*
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.GridLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*
import lab4.model.Direction.*

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
        val gamePanel = JPanel(GridLayout(gameModel.getBoardRow(), gameModel.getBoardColumn()))
        for (i in 0 until gameModel.getBoardRow()) {
            val buttonsRow = mutableListOf<JButton>()
            for (j in 0 until gameModel.getBoardColumn()) {
                val cellButton = JButton("")
                // get typed key information
                cellButton.addKeyListener(object : KeyAdapter() {
                    override fun keyTyped(e: KeyEvent) {
                        when (e.keyChar.toString().lowercase()) {
                            UP.toString() -> gameModel.doMove(UP)
                            DOWN.toString() -> gameModel.doMove(DOWN)
                            LEFT.toString() -> gameModel.doMove(LEFT)
                            RIGHT.toString() -> gameModel.doMove(RIGHT)
                        }
                    }
                })
                buttonsRow.add(cellButton)
                gamePanel.add(cellButton)
                updateFont(cellButton, 30.0f)
            }
            buttons.add(buttonsRow)
        }
        return gamePanel
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
        val state = gameModel.state
        statusLabel.text = state.textValue

        for ((i, buttonRow) in buttons.withIndex()) {
            for ((j, button) in buttonRow.withIndex()) {
                val cell = gameModel.board[i][j]
                when (cell) {
                    Cell.PLAYER -> button.text = cell.toString()
                    Cell.EXIT -> button.text = cell.toString()
                    Cell.WALL -> button.text = cell.toString()
                    else -> button.text = ""
                }

                UIManager.getDefaults()["Button.disabledText"] = Color.BLACK
                button.isEnabled = cell == Cell.PLAYER && state == State.MOVE
                if (cell == Cell.PLAYER) button.requestFocus()
                if (cell != Cell.WALL) {
                    button.background = Color.orange
                    button.foreground = Color.BLACK
                }
            }
        }
    }
}