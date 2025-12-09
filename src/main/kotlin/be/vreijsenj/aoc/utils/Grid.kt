package be.vreijsenj.aoc.utils

open class Grid(
    open val points: List<Point>,
    open val xMin: Long = points.minOf { it.x }, val xMax: Long = points.maxOf { it.x },
    open val yMin: Long = points.minOf { it.y }, val yMax: Long = points.maxOf { it.y }
) {

    companion object {

        @JvmStatic
        fun parse(xMax: Long, yMax: Long, xMin: Long = 0, yMin: Long = 0): Grid {
            return Grid(emptyList(), xMin, xMax, yMin, yMax)
        }

        fun rasterize(input: List<String>): List<Point> {
            return rasterize(input) { x, y, _ -> Point(x, y) }
        }

        fun <T> rasterize(input: List<String>, transformer: (Long, Long, Char) -> T): List<T> {
            val rows = input.map { it.toCharArray() }

            return rows.first().indices
                .flatMap { xIndex ->
                    rows.mapIndexed { yIndex, row ->
                        transformer(xIndex.toLong(), yIndex.toLong(), row[xIndex])
                    }
                }
        }
    }

    operator fun contains(point: Point): Boolean {
        return point.x in xMin..xMax && point.y in yMin..yMax
    }

    fun points(): List<Point> =
        points.ifEmpty {
            (xMin..xMax).flatMap { x ->
                (yMax..yMax).map { y ->
                    Point(x, y)
                }
            }
        }
}