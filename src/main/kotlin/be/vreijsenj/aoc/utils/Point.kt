package be.vreijsenj.aoc.utils

data class Point(val x: Long, val y: Long) {

    fun top() = Point(x, y - 1)
    fun topRight() = Point(x + 1, y - 1)
    fun topLeft() = Point(x - 1, y - 1)

    fun right() = Point(x + 1, y)
    fun left() = Point(x - 1, y)

    fun bottom() = Point(x, y + 1)
    fun bottomRight() = Point(x + 1, y + 1)
    fun bottomLeft() = Point(x - 1, y + 1)

    fun relative(xMax: Long, yMax: Long): Point {
        return Point(
            (x).mod(xMax + 1),
            (y).mod(yMax + 1)
        )
    }

    fun neighbours(diagonal: Boolean = false): List<Point> {
        if(diagonal) {
            return listOf(topLeft(), top(), topRight(), right(), bottomRight(), bottom(), bottomLeft(), left())
        }
        return listOf(top(), right(), left(), bottom())
    }

    fun next(direction: Direction): Point {
        return when(direction) {
            Direction.LEFT -> left()
            Direction.RIGHT -> right()
            Direction.DOWN -> bottom()
            Direction.UP -> top()
        }
    }
}