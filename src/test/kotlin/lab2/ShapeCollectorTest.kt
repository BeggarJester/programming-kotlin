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
        val shape1 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            1.5
        )
        val shape2 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            2.0
        )
        val shape3 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            6.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        assertEquals(
            true,
            myShapeCollector.getList().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        assertEquals(
            3,
            myShapeCollector.getListSize()
        )
        assertEquals(
            listOf(shape1, shape2, shape3),
            myShapeCollector.getList()
        )
    }

    @Test
    fun `test return the ShapeCollector size`() {
        val shape1 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            1.5
        )
        val shape2 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            2.0
        )
        val shape3 = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            6.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(
            0,
            myShapeCollector.getListSize()
        )
        myShapeCollector.insertShape2d(shape1)
        assertEquals(
            1,
            myShapeCollector.getListSize()
        )
        myShapeCollector.insertShape2d(shape2)
        assertEquals(
            2,
            myShapeCollector.getListSize()
        )
        myShapeCollector.insertShape2d(shape3)
        assertEquals(
            3,
            myShapeCollector.getListSize()
        )
    }

    @Test
    fun `test return created map by elements fill color`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Circle(
            colour1,
            colour1,
            1.5
        )
        val shape2 = Square(
            colour1,
            colour2,
            2.0
        )
        val shape3 = Rectangle(
            colour2,
            colour2,
            6.0,
            6.0
        )
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        assertEquals(
            true,
            myShapeCollector.getMapByFillColor().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        assertEquals(3, myShapeCollector.getListSize())
        assertEquals(
            mapOf(colour1 to listOf(shape1), colour2 to listOf(shape2, shape3)),
            myShapeCollector.getMapByFillColor()
        )
    }

    @Test
    fun `test return created map by elements border color`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Circle(colour1, colour1, 1.5)
        val shape2 = Square(colour1, colour2, 2.0)
        val shape3 = Rectangle(colour2, colour2, 6.0, 6.0)
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        assertEquals(
            true,
            myShapeCollector.getMapByBorderColor().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        assertEquals(3, myShapeCollector.getListSize())
        assertEquals(
            mapOf(colour1 to listOf(shape1, shape2), colour2 to listOf(shape3)),
            myShapeCollector.getMapByBorderColor()
        )
    }

    @Test
    fun `test return list of filtered elements by user shape type`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Square(colour1, colour1, 1.5)
        val shape2 = Rectangle(colour2, colour2, 6.0, 6.0)
        val shape3 = Square(colour1, colour2, 2.0)
        val shape4 = Rectangle(colour2, colour2, 6.0, 6.0)
        val myShapeCollector = ShapeCollector()
        assertEquals(0, myShapeCollector.getListSize())
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        myShapeCollector.insertShape2d(shape4)
        assertEquals(4, myShapeCollector.getListSize())
        assertEquals(
            listOf(shape1, shape3),
            myShapeCollector.filterByType("Square")
        )
        assertEquals(
            listOf(shape2, shape4),
            myShapeCollector.filterByType("Rectangle")
        )
        assertEquals(
            true,
            myShapeCollector.filterByType("Circle").isEmpty()
        )
    }

    @Test
    fun `test return the ShapeCollector by string`() {
        val colour1 = Color(255, 255, 255, 0)
        val shape = Circle(colour1, colour1, 1.5)
        val myShapeCollector = ShapeCollector()
        assertEquals(
            "",
            myShapeCollector.toString()
        )
        myShapeCollector.insertShape2d(shape)
        assertEquals(
            "Circle(borderColor=(red=255, green=255, blue=255, alpha=0), fillColor=(red=255, green=255, blue=255, alpha=0), radius=1.5)\n",
            myShapeCollector.toString()
        )
    }
}