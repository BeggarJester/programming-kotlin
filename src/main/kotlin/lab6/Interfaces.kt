package lab6

// interface of 2D shapes
interface Shape2d {
    fun calculateArea(): Double // shape area calculation
}

// interface of colored 2D shapes
interface ColoredShape2d : Shape2d {
    val borderColor: Color
    val fillColor: Color
}
