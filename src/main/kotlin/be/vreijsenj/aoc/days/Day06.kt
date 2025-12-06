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

    /**
     * By parsing the last line of the input, which contains all the operators, we know the range of each column.
     *
     *     123 328  51 64
     *      45 64  387 23
     *       6 98  215 314
     * --> *   +   *   +
     *     ^-^ ^-^ ^-^ ^-^
     *     0-2 4-6 6-8 8-10
     *
     * By iterating over the ranges for each line, we can create a column for our MathProblem.
     * On each column, replace the 'missing' values with 0s to make the calculations easier.
     *
     * MathProblem(operator = "*", values = ["123", "045", "006"])
     * MathProblem(operator = "+", values = ["328", "640", "980"])
     * MathProblem(operator = "*", values = ["051", "387", "215"])
     * MathProblem(operator = "+", values = ["064", "023", "314"])
     *
     * Part 1; we can simply remove all '0's and complete the calculation with the operator.
     * Part 2; we can simply take the numbers by index (as the 0's make sure all numbers are equal in length),
     *         then ignore the 0's when joining them back together.
     */
    fun parse(input: List<String>): List<MathProblem> {
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
        val problems = parse(input);

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
        val problems = parse(input).reversed();

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