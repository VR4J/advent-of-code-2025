package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.Direction
import be.vreijsenj.aoc.utils.Grid
import be.vreijsenj.aoc.utils.Point
import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.time.measureTime

object Day07 {

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInput(7, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun grid(input: List<String>): Triple<Grid, Point, List<Point>> {
        var start: Point = Point(0, 0)
        val splits: MutableList<Point> = mutableListOf()

        val points: List<Point> = Grid.rasterize(input) { x, y, value ->
            val point = Point(x, y)

            if (value == '^') {
                splits.add(point)
            }

            if (value == 'S') {
                start = point;
            }

            point
        }

        return Triple(Grid(points), start, splits);
    }

    fun runPartOne(input: List<String>): Int {
        val (grid, start, splits) = grid(input)

        val hits: MutableList<Point> = mutableListOf()

        fun traverse(start: Point, splits: List<Point>, grid: Grid) {
            val next = start.next(Direction.DOWN);

            if(next !in grid || next in hits) {
                // Stop when we reached the end of the grid, or we already hit this splitter.
                return
            }

            if(next in splits) {
                hits += next

                traverse(next.left(), splits, grid)
                traverse(next.right(), splits, grid)
            } else {
                traverse(next, splits, grid)
            }
        }

        traverse(start, splits, grid)

        return hits.size
    }

    fun runPartTwo(input: List<String>): Long {
        val (grid, start, splits) = grid(input)

        val memoize = hashMapOf<Point, Long>()

        fun traverse(position: Point, splits: List<Point>, grid: Grid): Long {
            memoize[position]?.let { return it }

            val next = position.next(Direction.DOWN);

            val result = when(next) {
                in splits -> {
                    traverse(next.left(), splits, grid) + traverse(next.right(), splits, grid)
                }
                !in grid -> 1
                else -> traverse(next, splits, grid)
            }

            memoize[position] = result

            return result
        }

        return traverse(start, splits, grid)
    }
}