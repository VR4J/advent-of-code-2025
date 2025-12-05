package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.math.max
import kotlin.time.measureTime

object Day05 {

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInputAsText(5, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun parse(input: String): Pair<List<LongRange>, List<String>> {
        val (ranges, ids) = input
            .split("\n\n")
            .map {
                it
                    .split("\n")
                    .filter { it.isNotBlank() }
                    .map { it.trim() }
            }

        val fresh = ranges.map {
            val (start, end) = it
                .split("-")
                .map { it.toLong() }

            start..end
        }

        return Pair(fresh, ids)
    }

    fun runPartOne(input: String): Int {
        val (fresh, ids) = parse(input)

        return ids
            .map { it.toLong() }
            .count { id -> fresh.any { id in it } }
    }

    fun runPartTwo(input: String): Long {
        val (fresh, ids) = parse(input)

        val sorted: MutableList<LongRange> = fresh
            .sortedBy { it.first }
            .toMutableList()

        val merged: MutableList<LongRange> = mutableListOf(
            sorted.removeFirst()
        )

        sorted.forEach { range ->
            val last = merged.last()

            val start = last.first
            val end = last.last;

            // Add the range if it does not intersect with any previous ranges
            if(end < range.first) {
                merged.add(range)
            } else {
                // Extend the range if it intersects
                val extended = start.rangeTo(
                    max(end, range.last)
                )

                // Replace last range with the extended range
                merged[merged.size -1] = extended
            }
        }

        return merged.sumOf { it.last - it.first + 1 };
    }
}