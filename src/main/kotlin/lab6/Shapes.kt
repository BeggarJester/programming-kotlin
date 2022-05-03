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
}