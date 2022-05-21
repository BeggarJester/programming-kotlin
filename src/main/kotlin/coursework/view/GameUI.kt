package coursework.view

import coursework.controller.Controller
import coursework.model.GameModel
import coursework.view.ColorOptions.*
import lab6.FileManager
import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

enum class ColorOptions(private val colorText: Color) {
    ColorTextButton(Color(45, 75, 45, 255)),
    ColorTextFinalScore(Color(0, 0, 0, 255)),
    ColorTextLeaderboardPanel(Color(255, 255, 255, 255)),
    ColorBackgroundButtonPanel(Color(0, 0, 0, 0));

    fun toColor(): Color {
        return colorText
    }
}

interface ModelChangeListener {

    fun gameOver(score: Int)
    fun gamePause()
}

class GameUI : JFrame("Well, just you wait!"), ModelChangeListener {
    private var bodyGame = GameModel()
    private var controllerGame = Controller(bodyGame)

    init {
        createFrame()
        createStartPanel()
        isVisible = true
    }

    // Setting parameters for JFrame
    private fun createFrame() {
        val icon = ImageIcon("src/main/kotlin/coursework/images/icon.png")
        iconImage = icon.image
        setSize(735, 475)
        isResizable = false
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
    }

    // Creating a panel with the main game menu
    private fun createStartPanel() {
        val startMenu = DrawPanel(ImageIcon("src/main/kotlin/coursework/images/layer/startMenu.png").image)
        val buttonStartPanel = JPanel(GridLayout(3, 1, 10, 10))
        buttonStartPanel.preferredSize = Dimension(200, 150)
        buttonStartPanel.background = ColorBackgroundButtonPanel.toColor()

        // Creating a button to start the game
        val buttonStart = JButton("PLAY")
        updateComponentStyle(buttonStart)
        buttonStart.addActionListener {
            bodyGame.startGame()
            addKeyListener(controllerGame)
            add(bodyGame)
            bodyGame.addModelChangeListener(this)
            isFocusable = true
            startMenu.isVisible = false
        }
        buttonStartPanel.add(buttonStart)

        // Creating a button to display the leaderboard
        val buttonLeaderBoard = JButton("LEADERBOARD")
        updateComponentStyle(buttonLeaderBoard)
        buttonLeaderBoard.addActionListener {
            createLeaderBoardPanel()
            startMenu.isVisible = false
        }
        buttonStartPanel.add(buttonLeaderBoard)

        // Creating a button to end the game
        val buttonFinish = JButton("FINISH")
        updateComponentStyle(buttonFinish)
        buttonFinish.addActionListener {
            dispose()
        }
        buttonStartPanel.add(buttonFinish)
        startMenu.add(buttonStartPanel)
        add(startMenu)
    }

    // Creating a panel with the final game menu
    private fun createFinishPanel(score: Int) {
        val finishMenu = DrawPanel(ImageIcon("src/main/kotlin/coursework/images/layer/finishMenu.png").image)
        val buttonFinishPanel = JPanel(GridLayout(5, 1, 10, 10))
        buttonFinishPanel.preferredSize = Dimension(230, 230)
        buttonFinishPanel.background = ColorBackgroundButtonPanel.toColor()

        // JLabel to display the result of the game
        val resultScore = JLabel("YOU RESULT: $score", SwingConstants.CENTER)
        updateComponentStyle(resultScore, 20.0f, ColorTextFinalScore.toColor())
        buttonFinishPanel.add(resultScore)

        // JTextField for the player to enter his nickname
        val textField = JTextField(10)
        buttonFinishPanel.add(textField)

        // Creating a button to save the game score
        val buttonSubmit = JButton("SUBMIT")
        updateComponentStyle(buttonSubmit)
        if (score == 0) buttonSubmit.isEnabled = false

        buttonSubmit.addActionListener {
            if (textField.text.isEmpty()) {
                JOptionPane.showConfirmDialog(
                    this,
                    "Please, enter a non-empty name!",
                    "Attention!",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.WARNING_MESSAGE,
                    null
                )
            } else {
                val leadersListFile = FileManager("src/main/kotlin/coursework/leaders.txt")
                val leadersListSerializer = SerializerManager()
                val leadersList = leadersListSerializer.deserialization(leadersListFile.read()).toMutableList()

                // if player with entered nick just have result in leaderboard
                if (leadersList.any { it.first == textField.text }) {
                    var index = 0
                    for (item in leadersList) {

                        // find player index in list
                        if (item.first == textField.text) {
                            index = leadersList.indexOf(item)
                        }
                    }

                    // if current result greater than previous
                    if (leadersList[index].second < score) {
                        leadersList.remove(leadersList[index]) // remove previous result
                        leadersList.add(Pair(textField.text, score)) // write current result
                    }
                } else leadersList.add(Pair(textField.text, score))
                leadersList.sortByDescending { it.second }
                leadersListFile.write(leadersListSerializer.serialization(leadersList))
                buttonSubmit.isEnabled = false
                val dialogOption = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to see the leaderboard?",
                    "Your result is saved!",
                    JOptionPane.YES_NO_OPTION,
                )
                if (dialogOption == JOptionPane.YES_OPTION) {
                    removeKeyListener(controllerGame)
                    remove(bodyGame)
                    bodyGame = GameModel()
                    controllerGame = Controller(bodyGame)
                    createLeaderBoardPanel()
                    finishMenu.isVisible = false
                }
            }
        }
        buttonFinishPanel.add(buttonSubmit)

        // Creating a button to restart the game
        val buttonRestart = JButton("RESTART")
        updateComponentStyle(buttonRestart)
        buttonRestart.addActionListener {
            finishMenu.remove(buttonFinishPanel)
            removeKeyListener(controllerGame)
            remove(bodyGame)
            bodyGame.removeModelChangeListener(this)
            bodyGame = GameModel()
            controllerGame = Controller(bodyGame)
            addKeyListener(controllerGame)
            add(bodyGame)
            bodyGame.addModelChangeListener(this)
            isFocusable = true
            finishMenu.isVisible = false
            bodyGame.startGame()
        }
        buttonFinishPanel.add(buttonRestart)

        // Creating a button to return to the main menu
        val buttonMainMenu = JButton("MAIN MENU")
        updateComponentStyle(buttonMainMenu)
        buttonMainMenu.addActionListener {
            finishMenu.isVisible = false
            addKeyListener(controllerGame)
            remove(bodyGame)
            bodyGame.removeModelChangeListener(this)
            bodyGame = GameModel()
            controllerGame = Controller(bodyGame)
            createStartPanel()
        }
        buttonFinishPanel.add(buttonMainMenu)
        finishMenu.add(buttonFinishPanel)
        add(finishMenu)
        bodyGame.isVisible = false
    }

    // Creating a window with a game pause menu
    private fun createPauseWindow() {
        val pauseWindow = JDialog(this, "Pause")
        pauseWindow.setSize(250, 250)
        pauseWindow.layout = GridLayout(3, 1, 20, 0)
        pauseWindow.isResizable = false
        pauseWindow.setLocationRelativeTo(null)

        // Closing the pause menu by pressing ESC
        pauseWindow.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_ESCAPE) {
                    pauseWindow.isVisible = false
                    bodyGame.startGame()
                }
            }
        })

        // Creating a button to resume the game
        val buttonResume = JButton("RESUME")
        updateComponentStyle(buttonResume, 25.0f)
        buttonResume.addActionListener {
            pauseWindow.isVisible = false
            bodyGame.startGame()
        }
        pauseWindow.add(buttonResume)

        // Creating a button to restart the game
        val buttonRestart = JButton("RESTART")
        updateComponentStyle(buttonRestart, 25.0f)
        buttonRestart.addActionListener {

            // Creating a dialog box warning about the completion of the game
            val dialogOption = JOptionPane.showConfirmDialog(
                this,
                "Game not finished, are you sure?",
                "Restart",
                JOptionPane.OK_CANCEL_OPTION
            )
            if (dialogOption == JOptionPane.OK_OPTION) {
                bodyGame.isVisible = false
                removeKeyListener(controllerGame)
                remove(bodyGame)
                bodyGame.removeModelChangeListener(this)
                bodyGame = GameModel()
                controllerGame = Controller(bodyGame)
                addKeyListener(controllerGame)
                add(bodyGame)
                bodyGame.addModelChangeListener(this)
                isFocusable = true
                pauseWindow.isVisible = false
                bodyGame.startGame()
            }
        }
        pauseWindow.add(buttonRestart)

        // Creating a button to return to the main menu
        val buttonFinish = JButton("MAIN MENU")
        updateComponentStyle(buttonFinish, 25.0f)
        buttonFinish.addActionListener {

            // Creating a dialog box warning about the completion of the game
            val dialogOption = JOptionPane.showConfirmDialog(
                this,
                "Game not finished, are you sure?",
                "Back to main menu",
                JOptionPane.OK_CANCEL_OPTION
            )
            if (dialogOption == JOptionPane.OK_OPTION) {
                pauseWindow.isVisible = false
                bodyGame.isVisible = false
                removeKeyListener(controllerGame)
                remove(bodyGame)
                bodyGame.removeModelChangeListener(this)
                bodyGame = GameModel()
                controllerGame = Controller(bodyGame)
                createStartPanel()
            }
        }
        pauseWindow.add(buttonFinish)
        pauseWindow.isFocusable = true
        pauseWindow.isVisible = true
    }

    // Creating a panel with leaderboard
    private fun createLeaderBoardPanel() {
        val leaderBoard = DrawPanel(ImageIcon("src/main/kotlin/coursework/images/layer/leaderBoard.png").image)
        val textLeaderBoard = JPanel(GridLayout(15, 1, 10, 0))
        textLeaderBoard.preferredSize = Dimension(400, 400)
        textLeaderBoard.background = ColorBackgroundButtonPanel.toColor()
        val leadersFile = FileManager("src/main/kotlin/coursework/leaders.txt")
        val leadersSerializer = SerializerManager()
        val leadersList = leadersSerializer.deserialization(leadersFile.read())

        // cosmetic empty lines
        for (index in 0..3) {
            textLeaderBoard.add(JLabel("", SwingConstants.LEFT))
        }
        for (index in 0..9) {
            val text = JLabel(
                "${index + 1}. ${leadersList[index].first}: ${leadersList[index].second}",
                SwingConstants.LEFT
            )
            updateComponentStyle(text, 20.0f, ColorTextLeaderboardPanel.toColor())
            textLeaderBoard.add(text)
        }
        leaderBoard.add(textLeaderBoard)

        // create button to return to the main menu
        val buttonMainMenu = JButton("MAIN MENU")
        updateComponentStyle(buttonMainMenu, 25.0f)
        buttonMainMenu.addActionListener {
            leaderBoard.isVisible = false
            createStartPanel()
            remove(buttonMainMenu)
        }
        add(buttonMainMenu, BorderLayout.SOUTH)
        add(leaderBoard)
    }

    private fun updateComponentStyle(
        component: JComponent,
        size: Float = 20.0f,
        color: Color = ColorTextButton.toColor()
    ) {
        val font = component.font
        component.font = font.deriveFont(size)
        component.foreground = color
    }

    override fun gameOver(score: Int) {
        bodyGame.removeModelChangeListener(this)
        createFinishPanel(score)
    }

    override fun gamePause() {
        createPauseWindow()
    }

    inner class DrawPanel(private val image: Image) : JPanel(GridBagLayout()) {
        override fun paintComponent(graphics: Graphics) {
            graphics.drawImage(image, 0, 0, this)
        }
    }

}