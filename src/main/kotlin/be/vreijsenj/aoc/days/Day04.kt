package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.Grid
import be.vreijsenj.aoc.utils.Point
import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.time.measureTime

object Day04 {

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInput(4, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun getPaperRolls(input: List<String>): List<Point> {
        val rolls: MutableList<Point> = mutableListOf()

        Grid.rasterize(input) { x, y, value ->
            if (value == '@') {
                val point = Point(x, y)
                rolls.add(point)
            }
        }

        return rolls;
    }

    fun runPartOne(input: List<String>): Int {
        val rolls: List<Point> = getPaperRolls(input)

        return rolls.count { point ->
            val adjacentPaperRolls = point.neighbours(diagonal = true)
                .count { rolls.contains(it) }

            adjacentPaperRolls < 4
        }
    }

    fun runPartTwo(input: List<String>): Int {
        val rolls: List<Point> = getPaperRolls(input)

        return remove(rolls, setOf()).size
    }

    fun remove(rolls: List<Point>, removed: Set<Point>): Set<Point> {
         val removable = rolls.filter { point ->
            val adjacentPaperRolls = point.neighbours(diagonal = true)
                .count { ! removed.contains(it) && rolls.contains(it) }

            adjacentPaperRolls < 4
        }

        if(removable.any { ! removed.contains(it) }) {
            return remove(rolls, removed + removable)
        }

        return removed;
    }
}