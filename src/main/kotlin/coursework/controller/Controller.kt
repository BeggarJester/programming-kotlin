package coursework.controller

import coursework.model.GameModel
import coursework.model.Position.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.ImageIcon

class Controller(private val model: GameModel) : KeyAdapter() {

    override fun keyPressed(e: KeyEvent?) {
        if (e!!.keyCode == KeyEvent.VK_DOWN) {
            when (model.wolfPosition) {
                UP_LEFT -> {
                    model.setWolfPosition(
                        ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-1.png").image,
                        DOWN_LEFT
                    )
                }
                UP_RIGHT -> {
                    model.setWolfPosition(
                        ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-3.png").image,
                        DOWN_RIGHT
                    )
                }
                else -> {}
            }
        }
        if (e.keyCode == KeyEvent.VK_UP) {
            when (model.wolfPosition) {
                DOWN_LEFT -> {
                    model.setWolfPosition(ImageIcon("src/main/kotlin/coursework/images/wolf/wolf.png").image, UP_LEFT)
                }
                DOWN_RIGHT -> {
                    model.setWolfPosition(
                        ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-2.png").image,
                        UP_RIGHT
                    )
                }
                else -> {}
            }
        }
        if (e.keyCode == KeyEvent.VK_RIGHT) {
            when (model.wolfPosition) {
                UP_LEFT -> {
                    model.setWolfPosition(
                        ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-2.png").image,
                        UP_RIGHT
                    )
                }
                DOWN_LEFT -> {
                    model.setWolfPosition(
                        ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-3.png").image,
                        DOWN_RIGHT
                    )
                }
                else -> {}
            }
        }
        if (e.keyCode == KeyEvent.VK_LEFT) {
            when (model.wolfPosition) {
                UP_RIGHT -> {
                    model.setWolfPosition(ImageIcon("src/main/kotlin/coursework/images/wolf/wolf.png").image, UP_LEFT)
                }
                DOWN_RIGHT -> {
                    model.setWolfPosition(
                        ImageIcon("src/main/kotlin/coursework/images/wolf/wolf-1.png").image,
                        DOWN_LEFT
                    )
                }
                else -> {}
            }
        }
        if (e.keyCode == KeyEvent.VK_ESCAPE) {
            model.pauseGame()
        }
    }
}