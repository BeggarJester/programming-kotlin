package coursework

import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*

interface GameChangeListener {
    fun gameOver(score: Int)
    fun gamePause()
}

class Game : JFrame("Well, just you wait!"), GameChangeListener {
    private var bodyGame = GameProcess()

    init {
        createFrame()
        createStartPanel()
        isVisible = true
    }

    private fun createFrame() {
        val icon = ImageIcon("src/main/kotlin/coursework/images/icon.png")
        iconImage = icon.image
        setSize(735, 475)
        isResizable = false
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
    }

    private fun createStartPanel() {
        val startMenu = DrawPanel(ImageIcon("src/main/kotlin/coursework/images/layer/startMenu.png").image)
        val buttonStartPanel = JPanel(GridLayout(3, 1, 10, 10))
        buttonStartPanel.preferredSize = Dimension(200, 150)
        buttonStartPanel.background = Color(0, 0, 0, 0)
        val buttonStart = JButton("PLAY")
        updateButtonStyle(buttonStart)
        buttonStart.addActionListener {
            bodyGame.startGame()
            addKeyListener(bodyGame)
            add(bodyGame)
            bodyGame.addModelChangeListener(this)
            isFocusable = true
            startMenu.isVisible = false
        }
        buttonStartPanel.add(buttonStart)

        val buttonLeaderBoard = JButton("LEADERBOARD")
        updateButtonStyle(buttonLeaderBoard)
        buttonLeaderBoard.addActionListener {
            createLeaderBoardPanel()
            startMenu.isVisible = false
        }
        buttonStartPanel.add(buttonLeaderBoard)

        val buttonFinish = JButton("FINISH")
        updateButtonStyle(buttonFinish)
        buttonFinish.addActionListener {
            dispose()
        }
        buttonStartPanel.add(buttonFinish)
        startMenu.add(buttonStartPanel)
        add(startMenu)
    }

    private fun createFinishPanel(score: Int) {
        val finishMenu = DrawPanel(ImageIcon("src/main/kotlin/coursework/images/layer/finishMenu.png").image)
        val buttonFinishPanel = JPanel(GridLayout(5, 1, 10, 10))
        buttonFinishPanel.preferredSize = Dimension(230, 230)
        buttonFinishPanel.background = Color(0, 0, 0, 0)
        val resultScore = JLabel("YOU RESULT: $score", SwingConstants.CENTER)
        val font = resultScore.font
        val derivedFont = font.deriveFont(20.0f)
        resultScore.font = derivedFont
        buttonFinishPanel.add(resultScore)
        val textField = JTextField(10)
        buttonFinishPanel.add(textField)

        val buttonSubmit = JButton("SUBMIT")
        updateButtonStyle(buttonSubmit)
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
                val mySerializer = Serializer1()
                val temporaryList = mySerializer.deserialization()

                if (temporaryList.any { it.first == textField.text }) {
                    var previousScore = score
                    var index = 0
                    for (item in temporaryList) {
                        if (item.first == textField.text) {
                            index = temporaryList.indexOf(item)
                        }
                    }
                    temporaryList.remove(temporaryList[index])
                    if (temporaryList[index].second >= score) previousScore = temporaryList[index].second
                    temporaryList.add(Pair(textField.text, previousScore))
                } else temporaryList.add(Pair(textField.text, score))

                temporaryList.sortByDescending { it.second }
                mySerializer.serialization(temporaryList)
                buttonSubmit.isEnabled = false
            }
        }
        buttonFinishPanel.add(buttonSubmit)

        val buttonRestart = JButton("RESTART")
        updateButtonStyle(buttonRestart)
        buttonRestart.addActionListener {
            finishMenu.remove(buttonFinishPanel)
            removeKeyListener(bodyGame)
            remove(bodyGame)
            bodyGame = GameProcess()
            addKeyListener(bodyGame)
            add(bodyGame)
            bodyGame.addModelChangeListener(this)
            isFocusable = true
            finishMenu.isVisible = false
            bodyGame.startGame()
        }
        buttonFinishPanel.add(buttonRestart)
        val buttonMainMenu = JButton("MAIN MENU")
        updateButtonStyle(buttonMainMenu)
        buttonMainMenu.addActionListener {
            finishMenu.isVisible = false
            removeKeyListener(bodyGame)
            remove(bodyGame)
            bodyGame.removeModelChangeListener(this)
            bodyGame = GameProcess()
            createStartPanel()
        }
        buttonFinishPanel.add(buttonMainMenu)
        finishMenu.add(buttonFinishPanel)
        add(finishMenu)
        bodyGame.isVisible = false
    }

    private fun createPausePanel() {
        val jd = JDialog(this, "Pause")
        jd.setSize(250, 250)
        jd.layout = GridLayout(3, 1, 20, 0)
        jd.isResizable = false
        jd.setLocationRelativeTo(null)

        jd.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_ESCAPE) {
                    jd.isVisible = false
                    bodyGame.startGame()
                }
            }
        })

        val buttonResume = JButton("RESUME")
        updateButtonStyle(buttonResume)
        buttonResume.addActionListener {
            jd.isVisible = false
            bodyGame.startGame()
        }
        jd.add(buttonResume)

        val buttonRestart = JButton("RESTART")
        updateButtonStyle(buttonRestart)
        buttonRestart.addActionListener {
            bodyGame.isVisible = false
            removeKeyListener(bodyGame)
            remove(bodyGame)
            bodyGame.removeModelChangeListener(this)
            bodyGame = GameProcess()
            addKeyListener(bodyGame)
            add(bodyGame)
            bodyGame.addModelChangeListener(this)
            isFocusable = true
            jd.isVisible = false
            bodyGame.startGame()
        }
        jd.add(buttonRestart)

        val buttonFinish = JButton("MAIN MENU")
        updateButtonStyle(buttonFinish)
        buttonFinish.addActionListener {
            jd.isVisible = false
            bodyGame.isVisible = false
            removeKeyListener(bodyGame)
            remove(bodyGame)
            bodyGame.removeModelChangeListener(this)
            bodyGame = GameProcess()
            createStartPanel()
        }
        jd.add(buttonFinish)
        jd.isFocusable = true
        jd.isVisible = true
    }

    private fun createLeaderBoardPanel() {
        val leaderBoard = DrawPanel(ImageIcon("src/main/kotlin/coursework/images/layer/leaderBoard.png").image)
        add(leaderBoard)

        val textLeaderBoard = JPanel(GridLayout(15, 1, 10, 0))
        textLeaderBoard.preferredSize = Dimension(400, 500)
        textLeaderBoard.background = Color(0, 0, 0, 0)

        val mySerializer = Serializer1()
        val temporaryList = mySerializer.deserialization()
        for (index in 0..3) {
            textLeaderBoard.add(JLabel("", SwingConstants.LEFT))
        }
        for (index in 0..9) {
            val text = JLabel("${index + 1}. ${temporaryList[index]}", SwingConstants.LEFT)
            val font = text.font
            val derivedFont = font.deriveFont(20.0f)
            text.font = derivedFont
            textLeaderBoard.add(text)
            text.foreground = Color(255, 255, 255, 255)
        }
        leaderBoard.add(textLeaderBoard)
        val buttonMainMenu = JButton("MAIN MENU")
        updateButtonStyle(buttonMainMenu)
        buttonMainMenu.addActionListener {
            leaderBoard.isVisible = false
            createStartPanel()
            remove(buttonMainMenu)
        }
        add(buttonMainMenu, BorderLayout.SOUTH)

    }

    private fun updateButtonStyle(button: JButton) {
        val font = button.font
        val derivedFont = font.deriveFont(25.0f)
        button.font = derivedFont
        button.foreground = Color(45, 75, 45, 255)
    }

    override fun gameOver(score: Int) {
        bodyGame.removeModelChangeListener(this)
        createFinishPanel(score)
    }

    override fun gamePause() {
        createPausePanel()
    }

}