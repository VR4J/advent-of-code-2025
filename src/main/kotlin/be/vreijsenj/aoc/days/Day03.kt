package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.time.measureTime

object Day03 {

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInput(3, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun runPartOne(input: List<String>): Int {
        val banks: List<CharArray> = input.map { it.toCharArray() }

        return banks.sumOf {
            val max = it.filterIndexed { pos, _ -> pos < it.size - 1 }.maxBy { it.code }
            val index = it.indexOf(max);

            val second = it
                .filterIndexed { pos, _ -> pos > index }
                .maxBy { it.code }

            "$max$second".toInt();
        }
    }

    fun runPartTwo(input: List<String>): Long {
        val banks: List<CharArray> = input.map { it.toCharArray() }

        return banks.sumOf { bank ->
            var position: Int = 0
            val output: MutableList<Char> = mutableListOf()

            repeat(12) { iteration ->
                // Every iteration we have to make sure we have enough numbers left to make the 12 digits.
                val limit = (bank.size - 1) - (11 + position - iteration) + position
                val (index, value) = getHighestBattery(bank, position, limit)

                position = index + 1

                output.add(value)
            }

            output.joinToString("").toLong()
        }
    }

    fun getHighestBattery(bank: CharArray, pos: Int, limit: Int): Pair<Int, Char> {
        val unused = bank.slice(pos..limit)
        val max = unused.maxBy { it.code }
        val index = pos + unused.indexOf(max);

        return Pair(index, max)
    }
}