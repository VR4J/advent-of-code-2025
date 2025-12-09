package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.Edge
import be.vreijsenj.aoc.utils.Point
import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.math.abs
import kotlin.time.measureTime

object Day09 {

    data class Square(val xMin: Double, val xMax: Double, val yMin: Double, val yMax: Double) {

        operator fun contains(point: Edge.Position): Boolean {
            return point.x > xMin && point.x < xMax && point.y > yMin && point.y < yMax
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInput(9, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun runPartOne(input: List<String>): Long {
        val tiles: List<Point> = input.map {
            val (x, y) = it.split(",").map { it.toLong() }

            Point(x, y)
        }

        val pairs: List<Pair<Point, Point>> = tiles
            .flatMap {
                tiles
                    .filter { other -> it != other }
                    .map { other -> it to other }
            }
            .distinctBy { setOf(it.first, it.second) }

        return pairs.maxOf { (first, second) ->
            val l = abs(first.y - second.y) + 1
            val w = abs(first.x - second.x) + 1

            l * w
        }
    }

    /**
     * We can create the edges of the polygon by connecting the red tiles in a straight line.
     * When we have the edges of the polygon, we can use ray-casting (as used in AOC-2023 Day 10)
     * to check whether all points of an area are within the polygon.
     */
    fun runPartTwo(input: List<String>): Long {
        val reds: List<Edge.Position> = input.map {
            val (x, y) = it.split(",").map { it.toDouble() }

            Edge.Position(x, y)
        }

        val edges: List<Edge> = edges(reds)

        val pairs: List<Pair<Edge.Position, Edge.Position>> = reds
            .flatMap {
                reds
                    .filter { other -> it != other }
                    .map { other -> it to other }
            }
            .distinctBy { setOf(it.first, it.second) }

        var iteration = 0L;

        val pOutline = edges.flatMap { it.start..it.end }

        // map to area of points
        return pairs.maxOf { (first, second) ->
            val l = abs(first.y - second.y) + 1
            val w = abs(first.x - second.x) + 1

            val outers: List<Edge.Position> = listOf(
                first,
                Edge.Position(first.x, second.y),
                second,
                Edge.Position(second.x, first.y)
            )

            val square = Square(
                outers.minOf { it.x },
                outers.maxOf { it.x },
                outers.minOf { it.y },
                outers.maxOf { it.y }
            )

            val memoize: HashMap<Edge.Position, Boolean> = hashMapOf()

            fun inPolygon(position: Edge.Position): Boolean {
                memoize[position]?.let {
                    println("Cache hit")
                    return it
                }

                val result = edges.count { it(position) } % 2 != 0

                memoize[position] = result

                return result;
            }

            val inPolygon = outers
                .filter { point ->
                    edges.none { it.contains(point) }
                }
                .all { position -> inPolygon(position) }

            // Check whether a point of the polygon is within the area, that means only the corners are in
            val isPartial = pOutline.any {
                it in square
            }

            print("Iteration ${++iteration}/${pairs.size} (${iteration/pairs.size*100}%)\r")

            if(inPolygon && ! isPartial) {
                l.toLong() * w.toLong()
            } else {
                0L
            }
        }
    }

    fun edges(points: List<Edge.Position>): List<Edge> {
        val edges = emptyList<Edge>().toMutableList()

        points.windowed(2, 1) { (current, next) ->
            val direction = if(next.x != current.x) "x" else "y"

            if(direction == "y") {
                if(next.x == current.x) {
                    val edge = Edge(current, next)

                    edges.add(edge)
                }
            }

            if(direction == "x") {
                if(next.y == current.y) {
                    val edge = Edge(current, next)

                    edges.add(edge)
                }
            }
        }

        val edge = Edge(points.last(), points.first())

        return edges + edge
    }
}