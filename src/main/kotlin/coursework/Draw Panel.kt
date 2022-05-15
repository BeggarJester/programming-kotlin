package coursework

import java.awt.Graphics
import java.awt.GridBagLayout
import java.awt.Image
import javax.swing.JPanel

class DrawPanel(private val image: Image) : JPanel(GridBagLayout()) {
    override fun paintComponent(g: Graphics) {
        g.drawImage(image, 0, 0, this)
    }
}