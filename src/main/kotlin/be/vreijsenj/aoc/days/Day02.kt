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
                range.filter { 
                    isRepetitiveChunk(it) { index, id -> id.length % 2 == 0 && index == (id.length / 2) - 1} 
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
                range.filter { 
                    isRepetitiveChunk(it) { index, id -> index < id.length / 2 } 
                }
            }
            .sum()
    }


    fun isRepetitiveChunk(number: Long, isPossibleChunk: (Int, String) -> (Boolean)): Boolean {
        val id = number.toString()
        
    	return id.indices
            .filter { isPossibleChunk(it, id) }
            .map { it + 1 }
            .filter { chunk ->
                val value: String = id.take(chunk)
                val chunks = id.chunked(chunk)
    
                chunks.all { it == value }
            }
            .isNotEmpty()
    }
}
