package lab5

// class for colored 2d shapes collection storage
class ShapeCollector<out T: ColoredShape2d> {
    private val coloredShape2dList: MutableList<T> = mutableListOf()

    // add colored 2d shapes in collection
    fun insertShape2d(member: @UnsafeVariance T) {
        coloredShape2dList.add(member)
    }

    // add outside collection to our
    fun addAll(list : List<@UnsafeVariance T>) {
        list.forEach { coloredShape2dList.add(it) }
    }

    fun getSorted(typeOfSorting: Comparator<@UnsafeVariance T>): List<T> {
        return coloredShape2dList.sortedWith(typeOfSorting)
    }

    // return the list of colored 2d shapes with minimum area
    fun minArea(): List<T> {
        if (coloredShape2dList.size == 0) {
            return emptyList()
        }
        val minArea = coloredShape2dList.minOf { it.calculateArea() }
        return coloredShape2dList.filter { it.calculateArea() == minArea }
    }

    // return the list of colored 2d shapes with maximum area
    fun maxArea(): List<T> {
        if (coloredShape2dList.size == 0) {
            return emptyList()
        }
        val maxArea = coloredShape2dList.maxOf { it.calculateArea() }
        return coloredShape2dList.filter { it.calculateArea() == maxArea }
    }

    // return the sum of all colored 2d shapes areas
    fun sumAllAreas(): Double {
        return coloredShape2dList.sumOf { it.calculateArea() }
    }

    // return the list of colored 2d shapes sorted by user fill color
    fun searchByFillColor(searchColor: Color): List<T> {
        if (coloredShape2dList.size == 0) {
            return emptyList()
        }
        return coloredShape2dList.filter { it.fillColor == searchColor }
    }

    // return the list of colored 2d shapes sorted by user border color
    fun searchByBorderColor(searchColor: Color): List<T> {
        if (coloredShape2dList.size == 0) {
            return emptyList()
        }
        return coloredShape2dList.filter { it.borderColor == searchColor }
    }

    // return the list of colored 2d shapes
    fun getList(): List<T> {
        return coloredShape2dList
    }

    // return the size of list of colored 2d shapes sorted by user fill color
    fun getListSize(): Int {
        return coloredShape2dList.size
    }

    // return map created by user fill color
    fun getMapByFillColor(): Map<Color, List<T>> {
        return coloredShape2dList.groupBy { it.fillColor }
    }

    // return map created by user border color
    fun getMapByBorderColor(): Map<Color, List<T>> {
        return coloredShape2dList.groupBy { it.borderColor }
    }

    // return the list of colored 2d shapes sorted by user colored 2d shape type
    fun filterByType(typeName: String): List<T> {
        return coloredShape2dList.filter { it::class.simpleName == typeName }
    }

    // function to print the list of colored 2d shapes
    override fun toString(): String {
        var answer = ""
        coloredShape2dList.forEach { answer += it.toString() + "\n" }
        return answer
    }

}
