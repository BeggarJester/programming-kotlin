package lab6

import kotlinx.serialization.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlin.math.sqrt

val libSerializer = SerializersModule {
    polymorphic(ColoredShape2d::class) {
        subclass(Circle::class)
        subclass(Square::class)
        subclass(Rectangle::class)
        subclass(Triangle::class)
    }
}

// class for circle storage
@Serializable
@SerialName("lab6.Circle")
class Circle : ColoredShape2d {

    override val borderColor: Color
    override val fillColor: Color
    private val radius: Double

    constructor(borderColor: Color, fillColor: Color, radius: Double) {
        if (radius <= 0) {
            throw IllegalArgumentException("Circle with entered radius doesn't exist")
        }
        this.borderColor = borderColor
        this.fillColor = fillColor
        this.radius = radius
    }

    override fun calculateArea(): Double {
        return Math.PI * radius * radius
    }

    override fun toString(): String {
        return "Circle(borderColor=$borderColor, fillColor=$fillColor, radius=$radius)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Circle

        if (borderColor != other.borderColor) return false
        if (fillColor != other.fillColor) return false
        if (radius != other.radius) return false

        return true
    }

    override fun hashCode(): Int {
        var result = borderColor.hashCode()
        result = 31 * result + fillColor.hashCode()
        result = 31 * result + radius.hashCode()
        return result
    }

}

// class for square storage
@Serializable
@SerialName("lab6.Square")
class Square : ColoredShape2d {

    override val borderColor: Color
    override val fillColor: Color
    private val side: Double

    constructor(borderColor: Color, fillColor: Color, side: Double) {
        if (side <= 0) {
            throw IllegalArgumentException("Square with entered side doesn't exist")
        }
        this.borderColor = borderColor
        this.fillColor = fillColor
        this.side = side
    }

    override fun calculateArea(): Double {
        return side * side
    }

    override fun toString(): String {
        return "Square(borderColor=$borderColor, fillColor=$fillColor, side=$side)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Square

        if (borderColor != other.borderColor) return false
        if (fillColor != other.fillColor) return false
        if (side != other.side) return false

        return true
    }

    override fun hashCode(): Int {
        var result = borderColor.hashCode()
        result = 31 * result + fillColor.hashCode()
        result = 31 * result + side.hashCode()
        return result
    }
}

// class for rectangle storage
@Serializable
@SerialName("lab6.Rectangle")
class Rectangle : ColoredShape2d {

    override val borderColor: Color
    override val fillColor: Color
    private val height: Double
    private val width: Double

    constructor(borderColor: Color, fillColor: Color, height: Double, width: Double) {
        if (height <= 0 || width <= 0) {
            throw IllegalArgumentException("Rectangle with entered sides doesn't exist")
        }
        this.borderColor = borderColor
        this.fillColor = fillColor
        this.height = height
        this.width = width
    }

    override fun calculateArea(): Double {
        return height * width
    }

    override fun toString(): String {
        return "Rectangle(borderColor=$borderColor, fillColor=$fillColor, height=$height, width=$width)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rectangle

        if (borderColor != other.borderColor) return false
        if (fillColor != other.fillColor) return false
        if (height != other.height) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = borderColor.hashCode()
        result = 31 * result + fillColor.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + width.hashCode()
        return result
    }
}

// class for triangle storage
@Serializable
@SerialName("lab6.Triangle")
class Triangle : ColoredShape2d {
    override val borderColor: Color
    override val fillColor: Color
    private val firstSide: Double
    private val secondSide: Double
    private val thirdSide: Double

    constructor(borderColor: Color, fillColor: Color, firstSide: Double, secondSide: Double, thirdSide: Double) {
        if (firstSide + secondSide + thirdSide - 2 * maxOf(firstSide, secondSide, thirdSide) <= 0) {
            throw IllegalArgumentException("Triangle with entered sides doesn't exist")
        }
        this.borderColor = borderColor
        this.fillColor = fillColor
        this.firstSide = firstSide
        this.secondSide = secondSide
        this.thirdSide = thirdSide
    }

    override fun calculateArea(): Double {
        val halfMeter = (firstSide + secondSide + thirdSide) / 2
        return sqrt(halfMeter * (halfMeter - firstSide) * (halfMeter - secondSide) * (halfMeter - thirdSide))
    }

    override fun toString(): String {
        return "Triangle(borderColor=$borderColor, fillColor=$fillColor, firstSide=$firstSide, secondSide=$secondSide, thirdSide=$thirdSide)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Triangle

        if (borderColor != other.borderColor) return false
        if (fillColor != other.fillColor) return false
        if (firstSide != other.firstSide) return false
        if (secondSide != other.secondSide) return false
        if (thirdSide != other.thirdSide) return false

        return true
    }

    override fun hashCode(): Int {
        var result = borderColor.hashCode()
        result = 31 * result + fillColor.hashCode()
        result = 31 * result + firstSide.hashCode()
        result = 31 * result + secondSide.hashCode()
        result = 31 * result + thirdSide.hashCode()
        return result
    }
}