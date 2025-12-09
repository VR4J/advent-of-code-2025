package be.vreijsenj.aoc.utils

import kotlin.Double.Companion.MAX_VALUE
import kotlin.Double.Companion.MIN_VALUE
import kotlin.collections.map
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Edge(val start: Position, val end: Position) {

    data class Position(val x: Double, val y: Double) {

        operator fun rangeTo(that: Position): Set<Position> {
            if(this.x == that.x) {
                if(this.y > that.y) {
                    return generateSequence(this.y) { it - 1 }
                        .takeWhile { it >= that.y }
                        .map { Position(this.x, it) }
                        .toSet()
                }

                return generateSequence(this.y) { it + 1 }
                    .takeWhile { it <= that.y }
                    .map { Position(this.x, it) }
                    .toSet()
            }

            if(this.x > that.x) {
                return generateSequence(this.x) { it - 1 }
                    .takeWhile { it >= that.x }
                    .map { Position(it, this.y) }
                    .toSet()
            }

            return generateSequence(this.x) { it + 1 }
                .takeWhile { it <= that.x }
                .map { Position(it, this.y) }
                .toSet()
        }
    }

    /**
     * Ray casting implementation based on polygon edges.
     * https://en.wikipedia.org/wiki/Ray_casting
     */
    operator fun invoke(position: Position) : Boolean {
        // Swap vertical edge
        if(start.y > end.y) {
            return Edge(end, start).invoke(position)
        }

        // When vertical edge is exactly on start or end, move it slightly
        if(position.y == start.y || position.y == end.y) {
            return invoke(Position(position.x, position.y + epsilon))
        }

        // When outside vertical range, and is after horizontal range
        if(position.y > end.y || position.y < start.y || position.x > max(start.x, end.x)) {
            return false
        }

        // When inside vertical range, and is before horizontal range
        if(position.x < min(start.x, end.x)) {
            return true
        }

        // Areas
        val blue = if(abs(start.x - position.x) > MIN_VALUE) (position.y - start.y) / (position.x - start.x) else MAX_VALUE
        val red = if(abs(start.x - end.x) > MIN_VALUE) (end.y - start.y) / (end.x - start.x) else MAX_VALUE

        return blue >= red
    }

    operator fun contains(point: Position): Boolean {
        if(this.start.x == this.end.x) {
            return point.x == this.start.x && point.y >= min(this.start.y, this.end.y) && point.y <= max(this.start.y, this.start.y)
        }

        return point.y == this.start.y && point.x >= min(this.start.x, this.end.x) && point.x <= max(this.start.x, this.start.x)
    }

    private val epsilon = 0.00001
}