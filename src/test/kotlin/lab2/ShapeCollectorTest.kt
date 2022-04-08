package lab2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ShapeCollectorTest {

    @Test
    fun `test add element to ShapeCollector`() {
        val shape = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            1.5
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        myShapeCollector.insertShape2d(shape)
        assertEquals(1, myShapeCollector.getListSize())
        assertEquals(
            listOf(shape),
            myShapeCollector.getList()
        )
    }

    @Test
    fun `test return list of elements with minimum area`() {
        val shape1 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            2.0
        )
        val shape2 = Square(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            5.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        assertEquals(
            true,
            myShapeCollector.minArea().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        assertEquals(2, myShapeCollector.getListSize())
        assertEquals(
            listOf(shape1),
            myShapeCollector.minArea()
        )
    }

    @Test
    fun `test return list of elements with maximum area`() {
        val shape1 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            2.0
        )
        val shape2 = Square(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            5.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        assertEquals(
            true,
            myShapeCollector.maxArea().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        assertEquals(2, myShapeCollector.getListSize())
        assertEquals(
            listOf(shape2),
            myShapeCollector.maxArea()
        )
    }

    @Test
    fun `test return the sum of all elements areas`() {
        val shape1 = Rectangle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            2.0,
            5.0
        )
        val shape2 = Square(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            5.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(
            0,
            myShapeCollector.getListSize()
        )
        assertEquals(
            0.0,
            myShapeCollector.sumAllAreas()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        assertEquals(
            2,
            myShapeCollector.getListSize()
        )
        assertEquals(
            35.0,
            myShapeCollector.sumAllAreas()
        )
    }

    @Test
    fun `test return list of searched elements by user fill color`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Rectangle(
            colour1,
            colour1,
            2.0,
            5.0
        )
        val shape2 = Square(
            colour2,
            colour2,
            5.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        assertEquals(
            true,
            myShapeCollector.searchByFillColor(colour1).isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        assertEquals(2, myShapeCollector.getListSize())
        assertEquals(
            listOf(shape1),
            myShapeCollector.searchByFillColor(colour1)
        )
    }

    @Test
    fun `test return list of searched elements by user border color`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Rectangle(
            colour1,
            colour1,
            2.0,
            5.0
        )
        val shape2 = Square(
            colour2,
            colour2,
            5.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        assertEquals(
            true,
            myShapeCollector.searchByBorderColor(colour1).isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        assertEquals(2, myShapeCollector.getListSize())
        assertEquals(
            listOf(shape2),
            myShapeCollector.searchByBorderColor(colour2)
        )
    }

    @Test
    fun `test return the ShapeCollector`() {
    }

    @Test
    fun `test return the ShapeCollector size`() {
    }

    @Test
    fun `test return created map by elements fill color`() {
    }

    @Test
    fun `test return created map by elements border color`() {
    }

    @Test
    fun `test return list of filtered elements by user shape type`() {
    }

    @Test
    fun `test return the ShapeCollector by string`() {
    }
}