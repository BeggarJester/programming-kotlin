package lab5

class SortedByShapeArea : Comparator<ColoredShape2d> {
    override fun compare(firstShape: ColoredShape2d, secondShape: ColoredShape2d): Int {
        if(firstShape.calculateArea() < secondShape.calculateArea()) {
            return -1
        }
        if(firstShape.calculateArea() == secondShape.calculateArea()) {
            return 0
        }
        return 1
    }
}

class SortedByDescendingShapeArea : Comparator<ColoredShape2d> {
    override fun compare(firstShape: ColoredShape2d, secondShape: ColoredShape2d): Int {
        if(firstShape.calculateArea() < secondShape.calculateArea()) {
            return 1
        }
        if(firstShape.calculateArea() == secondShape.calculateArea()) {
            return 0
        }
        return -1
    }
}