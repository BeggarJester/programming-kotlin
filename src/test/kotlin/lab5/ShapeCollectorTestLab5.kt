package lab5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ShapeCollectorTestLab5 {

    @Test
    fun `test add outside collection to ShapeCollector`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Circle(colour1, colour1, 1.5)
        val shape2 = Circle(colour1, colour2, 3.0)
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        myShapeCollector.addAll(listOf(shape1, shape2))
        Assertions.assertEquals(
            listOf(shape1, shape2),
            myShapeCollector.getList()
        )
    }

    @Test
    fun `test sort ShapeCollector by Comparator`() {
        val colour1 = Color(255, 255, 255, 0)
        val colour2 = Color(1, 1, 1, 0)
        val shape1 = Square(colour2, colour1, 5.0)
        val shape2 = Square(colour2, colour2, 1.0)
        val shape3 = Square(colour2, colour1, 3.0)
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        Assertions.assertEquals(
            listOf(shape2, shape3, shape1),
            myShapeCollector.getSorted(SortedByShapeArea())
        )
        Assertions.assertEquals(
            listOf(shape1, shape3, shape2),
            myShapeCollector.getSorted(SortedByDescendingShapeArea())
        )
    }

    @Test
    fun `test add element to ShapeCollector`() {
        val shape = Circle(
            Color(255, 255, 255, 0),
            Color(1, 1, 1, 0),
            1.5
        )
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        myShapeCollector.insertShape2d(shape)
        Assertions.assertEquals(1, myShapeCollector.getListSize())
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        Assertions.assertEquals(
            true,
            myShapeCollector.minArea().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        Assertions.assertEquals(2, myShapeCollector.getListSize())
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        Assertions.assertEquals(
            true,
            myShapeCollector.maxArea().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        Assertions.assertEquals(2, myShapeCollector.getListSize())
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(
            0,
            myShapeCollector.getListSize()
        )
        Assertions.assertEquals(
            0.0,
            myShapeCollector.sumAllAreas()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        Assertions.assertEquals(
            2,
            myShapeCollector.getListSize()
        )
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        Assertions.assertEquals(
            true,
            myShapeCollector.searchByFillColor(colour1).isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        Assertions.assertEquals(2, myShapeCollector.getListSize())
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        Assertions.assertEquals(
            true,
            myShapeCollector.searchByBorderColor(colour1).isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        Assertions.assertEquals(2, myShapeCollector.getListSize())
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        Assertions.assertEquals(
            true,
            myShapeCollector.getList().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        Assertions.assertEquals(
            3,
            myShapeCollector.getListSize()
        )
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(
            0,
            myShapeCollector.getListSize()
        )
        myShapeCollector.insertShape2d(shape1)
        Assertions.assertEquals(
            1,
            myShapeCollector.getListSize()
        )
        myShapeCollector.insertShape2d(shape2)
        Assertions.assertEquals(
            2,
            myShapeCollector.getListSize()
        )
        myShapeCollector.insertShape2d(shape3)
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        Assertions.assertEquals(
            true,
            myShapeCollector.getMapByFillColor().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        Assertions.assertEquals(3, myShapeCollector.getListSize())
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        Assertions.assertEquals(
            true,
            myShapeCollector.getMapByBorderColor().isEmpty()
        )
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        Assertions.assertEquals(3, myShapeCollector.getListSize())
        Assertions.assertEquals(
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
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(0, myShapeCollector.getListSize())
        myShapeCollector.insertShape2d(shape1)
        myShapeCollector.insertShape2d(shape2)
        myShapeCollector.insertShape2d(shape3)
        myShapeCollector.insertShape2d(shape4)
        Assertions.assertEquals(4, myShapeCollector.getListSize())
        Assertions.assertEquals(
            listOf(shape1, shape3),
            myShapeCollector.filterByType("Square")
        )
        Assertions.assertEquals(
            listOf(shape2, shape4),
            myShapeCollector.filterByType("Rectangle")
        )
        Assertions.assertEquals(
            true,
            myShapeCollector.filterByType("Circle").isEmpty()
        )
    }

    @Test
    fun `test return the ShapeCollector by string`() {
        val colour1 = Color(255, 255, 255, 0)
        val shape = Circle(colour1, colour1, 1.5)
        val myShapeCollector = ShapeCollector<ColoredShape2d>()
        Assertions.assertEquals(
            "",
            myShapeCollector.toString()
        )
        myShapeCollector.insertShape2d(shape)
        Assertions.assertEquals(
            "Circle(borderColor=(red=255, green=255, blue=255, alpha=0), fillColor=(red=255, green=255, blue=255, alpha=0), radius=1.5)\n",
            myShapeCollector.toString()
        )
    }

}