package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.text.replace
import kotlin.text.toLong
import kotlin.text.trim
import kotlin.time.measureTime

object Day06 {

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInput(6, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    data class MathProblem(val operation: Char, val values: List<String>) {

        fun solve(parser: (List<String>) -> List<Long>): Long {
            val numbers = parser(values)

            return when(operation) {
                '*' -> numbers.reduce { acc, value -> acc * value }
                '+' -> numbers.reduce { acc, value -> acc + value }
                else -> throw RuntimeException("Unknown operation")
            }
        }
    }

    fun getMathProblems(input: List<String>): List<MathProblem> {
        val pattern = "([*+])(\\s+)".toRegex()
        val operations = input.last()

        return pattern.findAll(operations)
            .map { match ->
                val operation = match.value[0]
                val values = input
                    .filterIndexed { index, _ -> index < input.size - 1 }
                    .map {
                        // make sure every line is equal in length.
                        val normalized = it.padEnd(operations.length, ' ')

                        // collect values for column range
                        normalized.substring(match.range.first, match.range.last + 1)
                    }
                    .map {
                        // remove the trailing 'space' separator and replace 'missing' numbers with '0'.
                        when(it.last()) {
                            ' ' -> it
                                .substring(0, it.length - 1)
                                .replace(' ', '0')
                            else -> it.replace(' ', '0')
                        }
                    }
                    .toList()

                MathProblem(operation, values)
            }
            .toList()
    }

    fun runPartOne(input: List<String>): Long {
        val problems = getMathProblems(input);

        return problems.sumOf { problem ->
            problem.solve { values ->
                values
                    .map { it.trim() }
                    .map { it.replace("0", "") }
                    .map { it.toLong() }
            }
        }
    }

    fun runPartTwo(input: List<String>): Long {
        val problems = getMathProblems(input).reversed();

        return problems.sumOf { problem ->
            problem.solve { values ->
                val max = values.maxOf { it.length }

                (0..< max).reversed()
                    .map { index ->
                        values
                            .filter { (it.length - 1) >= index }
                            .map { it[index] }
                            .filter { it != '0' }
                            .joinToString("")
                    }
                    .map { it.toLong() }
            }
        }
    }
}