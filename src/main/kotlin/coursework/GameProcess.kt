package coursework

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.ImageIcon
import javax.swing.JComponent
import javax.swing.Timer

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

class GameProcess : JComponent(), ActionListener, KeyListener {
    var score = 0
    var lives = 3
    private var wolfPosition = Position.UP_LEFT
    private val img = ImageIcon("src/main/kotlin/coursework/images/layer/background.png").image
    private var wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf.png").image
    private var life = ImageIcon("src/main/kotlin/coursework/images/live/egg-live-$lives.png").image
    private val table: MutableList<MutableList<Image>> = initBoard()
    private var eggs: MutableList<Egg> = ArrayList()
    private var newEgg = 0
    private var complexity = 1
    private var delay = true

    private val timer: Timer = Timer(200) {

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
        notifyListeners()
    }

    override fun paintComponent(g: Graphics) {
        g.drawImage(img, 0, 0, null)
        g.drawImage(wolf, 0, 0, null)
        g.drawImage(life, 0, 0, null)

        val font: Font = font.deriveFont(42f)
        g.font = font
        g.color = Color.ORANGE
        val message = "SCORE: $score"
        g.drawString(message, 120, 60)

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
                life = ImageIcon("src/main/kotlin/coursework/images/live/egg-live-$lives.png").image
            }
            if ((counter == 7) && (index == wolfPosition.toString().toInt())) score++
            when (score) {
                5 -> complexity = 2
                10 -> complexity = 3
            }
            if (lives == 0) {
                stopGame()
            }
        }
    }

    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e!!.keyCode == KeyEvent.VK_DOWN) {
            when (wolfPosition) {
                Position.UP_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-1.png").image
                    wolfPosition = Position.DOWN_LEFT
                }
                Position.UP_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-3.png").image
                    wolfPosition = Position.DOWN_RIGHT
                }
                else -> {}
            }
            repaint()
        }
        if (e.keyCode == KeyEvent.VK_UP) {
            when (wolfPosition) {
                Position.DOWN_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf.png").image
                    wolfPosition = Position.UP_LEFT
                }
                Position.DOWN_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-2.png").image
                    wolfPosition = Position.UP_RIGHT
                }
                else -> {}
            }
            repaint()
        }
        if (e.keyCode == KeyEvent.VK_RIGHT) {
            when (wolfPosition) {
                Position.UP_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-2.png").image
                    wolfPosition = Position.UP_RIGHT
                }
                Position.DOWN_LEFT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-3.png").image
                    wolfPosition = Position.DOWN_RIGHT
                }
                else -> {}
            }
            repaint()
        }
        if (e.keyCode == KeyEvent.VK_LEFT) {
            when (wolfPosition) {
                Position.UP_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf.png").image
                    wolfPosition = Position.UP_LEFT
                }
                Position.DOWN_RIGHT -> {
                    wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-1.png").image
                    wolfPosition = Position.DOWN_LEFT
                }
                else -> {}
            }
            repaint()
        }
        if (e.keyCode == KeyEvent.VK_ESCAPE) {
            timer.stop()
            notifyListeners2()
        }
    }

    override fun keyReleased(e: KeyEvent?) {
    }

    override fun actionPerformed(e: ActionEvent?) {
        repaint()
    }

    private fun initBoard(): MutableList<MutableList<Image>> {
        val table1 = MutableList(4) {
            MutableList(7) { ImageIcon("src/main/kotlin/coursework/images/egg/0-0.png").image }
        }
        for (i in 0 until 4) {
            for (j in 0 until 7) {
                table1[i][j] = ImageIcon("src/main/kotlin/coursework/images/egg/$i-$j.png").image
            }
        }
        return table1
    }

    private val listeners: MutableSet<GameChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: GameChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: GameChangeListener) {
        listeners.remove(listener)
    }

    private fun notifyListeners() {
        listeners.forEach { it.gameOver(score) }
    }

    private fun notifyListeners2() {
        listeners.forEach { it.gamePause() }
    }

}