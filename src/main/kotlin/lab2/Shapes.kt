package lab2

import kotlin.math.sqrt

// class for circle storage
class Circle(override val borderColor: Color, override val fillColor: Color, radius: Double) : ColoredShape2d {
    private val radius: Double

    init {
        if (radius <= 0) {
            throw IllegalArgumentException("Circle with entered radius doesn't exist")
        }
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
class Square(override val borderColor: Color, override val fillColor: Color, side: Double) : ColoredShape2d {
    private val side: Double

    init {
        if (side <= 0) {
            throw IllegalArgumentException("Square with entered side doesn't exist")
        }
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
class Rectangle(
    override val borderColor: Color,
    override val fillColor: Color,
    height: Double,
    width: Double
) : ColoredShape2d {
    private val height: Double
    private val width: Double

    init {
        if (height <= 0 || width <= 0) {
            throw IllegalArgumentException("Rectangle with entered sides doesn't exist")
        }
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
class Triangle(
    override val borderColor: Color,
    override val fillColor: Color,
    firstSide: Double,
    secondSide: Double,
    thirdSide: Double
) : ColoredShape2d {
    private val firstSide: Double
    private val secondSide: Double
    private val thirdSide: Double

    init {
        if (firstSide + secondSide + thirdSide - 2 * maxOf(firstSide, secondSide, thirdSide) <= 0) {
            throw IllegalArgumentException("Triangle with entered sides doesn't exist")
        }
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
