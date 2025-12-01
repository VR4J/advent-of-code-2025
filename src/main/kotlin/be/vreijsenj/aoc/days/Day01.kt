package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.math.abs
import kotlin.time.measureTime

object Day01 {

    data class DialRotation(val amount: Int) {
        companion object {
            @JvmStatic
            fun parse(line: String): DialRotation {
                val amount = line.substring(1).toInt()

                return DialRotation(
                    if("L" in line) -amount else amount
                )
            }
        }

        fun execute(start: Int, max: Int): Int {
            return (start + amount).mod(max)
        }

        fun clicks(start: Int, max: Int): Pair<Int, Int> {
            val loops: Int = abs(amount / max)
            val rest: Int = amount % max

            val result = execute(start, max)

            if(rest == 0 || start == 0) {
                return Pair(result, loops)
            }

            if((start + rest) >= max || (start + rest) <= 0)  {
                return Pair(result, loops + 1)
            }

            return Pair(result, loops)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInput(1, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun runPartOne(input: List<String>): Int {
        val rotations: List<DialRotation> = input
            .map { DialRotation.parse(it) }

        val starts: MutableList<Int> = mutableListOf(50)

        rotations.forEach {
            starts.add(
                it.execute(starts.last(), 100)
            )
        }

        return starts.count { it == 0 }
    }

    fun runPartTwo(input: List<String>): Int {
        val rotations: List<DialRotation> = input
            .map { DialRotation.parse(it) }

        val starts: MutableList<Int> = mutableListOf(50)

        return rotations.sumOf {
            val (end, clicks) = it.clicks(starts.last(), 100)

            starts.add(end)

            clicks
        }
    }
}