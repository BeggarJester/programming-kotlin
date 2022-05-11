package CW

import CW.Position.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*

enum class Position(private val textValue: String) {
    UP_LEFT("0"),
    DOWN_LEFT("1"),
    UP_RIGHT("2"),
    DOWN_RIGHT("3");

    override fun toString(): String {
        return textValue
    }
}

enum class EggStatus {
    ROLLING,
    FALLING;
}

class Game : JFrame("Game") {

    private val body = GameProcess()
    private var startMenu = JPanel()
    private val finishMenu = JPanel()

    init {
        val icon = ImageIcon("src/main/kotlin/coursework/images/icon.png")
        iconImage = icon.image
        setSize(735, 475)
        isResizable = false
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        add(createStartPanel(startMenu))

        isVisible = true
    }

    private fun createStartPanel(start: JPanel): JPanel {
        start.background = Color.GREEN
        start.isVisible = true
        val button = JButton("Start")
        start.add(button)

        button.addActionListener {
            body.startGame()
            start.isVisible = false
            addKeyListener(body)
            add(body)
            body.isVisible = true
        }
        return start
    }

}

class GameProcess : JComponent(), KeyListener, ActionListener {
    var score = 0
    var lives = 3
    private var wolfPosition = UP_LEFT
    private val img = ImageIcon("src/main/kotlin/CW/background.png").image
    private var wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf.png").image
    private var life = ImageIcon("src/main/kotlin/coursework/images/frames/egg-live-$lives.png").image
    private val table: MutableList<MutableList<Image>> = initBoard()
    private var eggs: MutableList<GameProcess.Egg> = ArrayList()
    private var newEgg = 0
    private var complexity = 1
    private var delay = true
    val timer: Timer = Timer(500) {

        if (newEgg < complexity) {
            eggs.add(Egg((0..3).random()))
            delay = true
        }

        newEgg = 0
        if (delay) {
            newEgg++
            delay = false
        }

        for (egg in eggs) {
            egg.fall()
            egg.checkLivesAndScore()
            if (egg.status == EggStatus.ROLLING) newEgg++
            repaint()
        }
    }

    fun startGame() {

        timer.start()

    }

    fun stopGame() {

        timer.stop()

    }

    override fun paintComponent(g: Graphics) {
        g.drawImage(img, 0, 0, null)
        g.drawImage(wolf, 0, 0, null)
        g.drawImage(life, 0, 0, null)
        //g.drawString("SCORE: $score", 50, 50)

        val font: Font = font.deriveFont(42f)
        g.font = font
        g.color = Color.ORANGE
        val message = "SCORE: $score"
        val metrics: FontMetrics = g.fontMetrics
        g.drawString(message, 100, 70)

        for (egg in eggs) egg.drawEgg(g)
    }

    inner class Egg(private var index: Int, private var counter: Int = 0) {
        var status = EggStatus.ROLLING

        fun drawEgg(g: Graphics) {
            if (counter < 7) g.drawImage(table[index][counter], 0, 0, null)
        }

        fun fall() {
            status = if (counter < 8) {
                counter++
                EggStatus.ROLLING
            } else EggStatus.FALLING
        }

        fun checkLivesAndScore() {
            if ((counter == 7) && (index != wolfPosition.toString().toInt())) {
                lives--
                life = ImageIcon("src/main/kotlin/coursework/images/frames/egg-live-$lives.png").image
            }
            if ((counter == 7) && (index == wolfPosition.toString().toInt())) score++
            when (score) {
                5 -> complexity = 2
                10 -> complexity = 3
            }
            if (lives == 0) {
                timer.stop()
            }
        }
    }

    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e!!.keyCode == KeyEvent.VK_DOWN) {
            when (wolfPosition) {
                UP_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf-1.png").image
                    wolfPosition = DOWN_LEFT
                }
                UP_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf-3.png").image
                    wolfPosition = DOWN_RIGHT
                }
                else -> {}
            }
            repaint()
        }
        if (e.keyCode == KeyEvent.VK_UP) {
            when (wolfPosition) {
                DOWN_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf.png").image
                    wolfPosition = UP_LEFT
                }
                DOWN_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf-2.png").image
                    wolfPosition = UP_RIGHT
                }
                else -> {}
            }
            repaint()
        }
        if (e.keyCode == KeyEvent.VK_RIGHT) {
            when (wolfPosition) {
                UP_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf-2.png").image
                    wolfPosition = UP_RIGHT
                }
                DOWN_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf-3.png").image
                    wolfPosition = DOWN_RIGHT
                }
                else -> {}
            }
            repaint()
        }
        if (e.keyCode == KeyEvent.VK_LEFT) {
            when (wolfPosition) {
                UP_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf.png").image
                    wolfPosition = UP_LEFT
                }
                DOWN_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/CW/wolfImage/wolf-1.png").image
                    wolfPosition = DOWN_LEFT
                }
                else -> {}
            }
            repaint()
        }
    }

    override fun keyReleased(e: KeyEvent?) {
    }

    override fun actionPerformed(e: ActionEvent?) {
        repaint()
    }

    private fun initBoard(): MutableList<MutableList<Image>> {
        val table1 = MutableList(4) {
            MutableList(7) { ImageIcon("src/main/kotlin/CW/egg/0-0.png").image }
        }
        for (i in 0 until 4) {
            for (j in 0 until 7) {
                table1[i][j] = ImageIcon("src/main/kotlin/CW/egg/$i-$j.png").image
            }
        }
        return table1
    }


}

fun main() {
    SwingUtilities.invokeLater {
        val game = Game()
        game.isVisible = true
    }
}