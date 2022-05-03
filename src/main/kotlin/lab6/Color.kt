package lab6

import kotlinx.serialization.*

// class for color information storage
@Serializable
class Color {
    private val red: Int
    private val green: Int
    private val blue: Int
    private val alpha: Int

    constructor(red: Int, green: Int, blue: Int, alpha: Int) {
        if (red !in 0..255 || green !in 0..255 || blue !in 0..255 || alpha !in 0..100) {
            throw IllegalArgumentException("Color there isn't in RGBA format")
        }
        this.red = red
        this.green = green
        this.blue = blue
        this.alpha = alpha
    }

    override fun toString(): String {
        return "(red=$red, green=$green, blue=$blue, alpha=$alpha)"
    }
}