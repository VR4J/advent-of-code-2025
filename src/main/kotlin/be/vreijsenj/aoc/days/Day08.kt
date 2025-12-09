package be.vreijsenj.aoc.days

import be.vreijsenj.aoc.utils.Point3D
import be.vreijsenj.aoc.utils.PuzzleUtils
import kotlin.time.measureTime

object Day08 {

    data class Circuit(val boxes: Set<Point3D>) {

        fun merge(other: Circuit): Circuit {
            return Circuit(boxes + other.boxes)
        }

        operator fun plus(other: Point3D): Circuit {
            return Circuit(boxes + other)
        }

        operator fun contains(point: Point3D): Boolean {
            return point in boxes;
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val elapsed = measureTime {
            val input = PuzzleUtils.getInput(8, 1)

            val resultPartOne = runPartOne(input);
            val resultPartTwo = runPartTwo(input);

            println("Result (pt.1): $resultPartOne")
            println("Result (pt.2): $resultPartTwo")
        }

        println("Took $elapsed")
    }

    fun parse(input: List<String>): Pair<List<Circuit>, MutableList<Pair<Point3D, Point3D>>> {
        val circuits: List<Circuit> = input
            .map {
                val (x, y, z) = it
                    .split(",")
                    .map { it.toDouble() }

                Point3D(x, y, z)
            }
            .map { setOf(it) }
            .map { Circuit(it) }

        val boxes: List<Point3D> = circuits.flatMap { it.boxes }
        val closest: MutableList<Pair<Point3D, Point3D>> = boxes
            .flatMap {
                boxes
                    .filter { other -> it != other }
                    .map { other -> it to other }
            }
            .distinctBy { setOf(it.first, it.second) }
            .sortedBy { (left, right) ->
                left.distance(right)
            }
            .toMutableList()

        return Pair(circuits, closest)
    }

    fun runPartOne(input: List<String>): Int {
        var (circuits, closest) = parse(input);

        // 1000 for non-example
        repeat(10) {
            circuits = connect(circuits, closest) { }
        }

        return circuits
            .map { it.boxes.size }
            .sortedByDescending { it }
            .take(3)
            .reduce { acc, next -> acc * next }
    }

    fun runPartTwo(input: List<String>): Long {
        var (circuits, closest) = parse(input);

        var result = 0L

        while(circuits.size > 1) {
            circuits = connect(circuits, closest) { connection ->
                result = (connection.first.x * connection.second.x).toLong()
            }
        }

        return result
    }

    fun connect(circuits: List<Circuit>, closest: MutableList<Pair<Point3D, Point3D>>, onConnect: (Pair<Point3D, Point3D>) -> Unit): List<Circuit> {
        val next: Pair<Point3D, Point3D> = closest.removeFirst()

        if(circuits.any { next.first in it && next.second in it } ) {
            return circuits
        }

        val merged = circuits
            .filter {
                next.first in it || next.second in it
            }
            .reduce { acc, circuit -> acc.merge(circuit) }

        val others = circuits.filter { next.first !in it && next.second !in it  }

        onConnect(next)

        return others + merged
    }
}