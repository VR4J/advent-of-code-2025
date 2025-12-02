package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.math.abs
import kotlin.time.measureTime

object Day02 {

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInputAsText(2, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun runPartOne(input: String): Long {
        val ranges: List<LongRange> = input
            .split(",")
            .map {
                val (start, end) = it.split("-")
                LongRange(start.toLong(), end.toLong())
            }

        return ranges
            .flatMap { range ->
                range
                    .filter {
                        val value = it.toString()
                        val first = value.take(value.length / 2)
                        val second = value.substring(value.length / 2)

                        first == second
                    }
            }
            .sum()
    }

    fun runPartTwo(input: String): Long {
        val ranges: List<LongRange> = input
            .split(",")
            .map {
                val (start, end) = it.split("-")
                LongRange(start.toLong(), end.toLong())
            }

        return ranges
            .flatMap { range ->
                range
                    .filter { number ->
                        val id = number.toString()

                        val result = id.indices
                            .filter { it < id.length - 1 }
                            .map { it + 1 }
                            .mapNotNull { chunk ->
                                val value: String = id.take(chunk)
                                val chunks = id.chunked(chunk)

                                if(chunks.all { it == value }) id else null
                            }

                        result.isNotEmpty()
                    }
            }
            .sum()
    }
}