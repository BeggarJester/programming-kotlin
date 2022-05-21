package coursework

import java.awt.Color
import java.awt.Graphics
import java.awt.Image
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

class GameModel : JComponent() {
    var score = 0
    var lives = 3
    var wolfPosition = Position.UP_LEFT
    private val background = ImageIcon("src/main/kotlin/coursework/images/layer/background.png").image
    private var wolf = ImageIcon("src/main/kotlin/coursework/images/wolf/wolf.png").image
    private var life = ImageIcon("src/main/kotlin/coursework/images/live/egg-live-$lives.png").image
    private var eggs: MutableList<Egg> = ArrayList()
    private var currentEgg = 0
    private var sameTimeEggs = 1
    private var delay = true

    // one game step in time
    private val timer: Timer = Timer(200) {

        // if now rolling less eggs than current maximum rolling eggs number
        if (currentEgg < sameTimeEggs) {
            eggs.add(Egg((0..3).random())) // generate new egg
            delay = true // remind delay for next step
        }

        currentEgg = 0 // clear current rolling eggs counter

        // if egg was generated on previous step
        if (delay) {
            currentEgg++ // make a delay in eggs generation
            delay = false
        }

        // go through all rolling eggs
        for (egg in eggs) {
            egg.fall() // change its positions
            egg.checkLivesAndScores() // if some egg position is rolling end
            if (egg.status == EggStatus.ROLLING) currentEgg++ // current rolling eggs counter
            egg.removeFallingEgg() // if egg is fell, remove it
            repaint() // repaint game
        }
    }

    // resume timer
    fun startGame() {
        timer.start()
    }

    // stop game by stop timer & notify listeners about it
    fun stopGame() {
        timer.stop()
        notifyListenersOfGameOver()
    }

    // repaint game process
    override fun paintComponent(graphics: Graphics) {
        graphics.drawImage(background, 0, 0, null)
        graphics.drawImage(wolf, 0, 0, null)
        graphics.drawImage(life, 0, 0, null)
        graphics.font = font.deriveFont(42f)
        graphics.color = Color.ORANGE
        val scoreCounter = "SCORE: $score"
        graphics.drawString(scoreCounter, 120, 60)
        for (egg in eggs) egg.drawEgg(graphics)
    }

    // class of one rolling egg
    inner class Egg(private var index: Int) {
        private var counter: Int = 0
        var status = EggStatus.ROLLING

        // draw current egg position
        fun drawEgg(graphics: Graphics) {
            if (counter < 7) graphics.drawImage(
                ImageIcon("src/main/kotlin/coursework/images/egg/$index-$counter.png").image,
                0,
                0,
                null
            )
        }

        // change current egg position
        fun fall() {
            status = if (counter < 8) {
                counter++
                EggStatus.ROLLING
            } else EggStatus.FALLING
        }

        // when egg is fell, change scores or lives counter
        fun checkLivesAndScores() {
            if ((counter == 7) && (index != wolfPosition.toString().toInt())) {
                lives--
                life = ImageIcon("src/main/kotlin/coursework/images/live/egg-live-$lives.png").image
            }
            if ((counter == 7) && (index == wolfPosition.toString().toInt())) score++

            // if wolf collected some eggs, same-time rolling eggs  number increase
            when (score) {
                5 -> sameTimeEggs = 2
                10 -> sameTimeEggs = 3
            }

            // if game lose
            if (lives == 0) {
                stopGame()
            }
        }

        // if egg is falling, remove it from eggs list
        fun removeFallingEgg() {
            if (counter > 8) eggs.remove(this)
        }

    }

    // repaint received wolf position
    fun setWolfPosition(image: Image, position: Position) {
        wolf = image
        wolfPosition = position
        repaint()
    }

    // pause game by stop timer & notify listeners about it
    fun pauseGame() {
        timer.stop()
        notifyListenersOfPause()
    }

    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: ModelChangeListener) {
        listeners.add(listener)
    }

    fun removeModelChangeListener(listener: ModelChangeListener) {
        listeners.remove(listener)
    }

    private fun notifyListenersOfGameOver() {
        listeners.forEach { it.gameOver(score) }
    }

    private fun notifyListenersOfPause() {
        listeners.forEach { it.gamePause() }
    }

}