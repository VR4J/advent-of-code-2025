package be.vreijsenj.aoc.utils

import kotlin.math.sqrt

open class Point3D(open val x: Double, open val y: Double, open val z: Double) {

    fun above() = Point3D(x, y, z + 1)
    fun below() = Point3D(x, y, z - 1)

    override fun equals(other: Any?): Boolean {
        if(other !is Point3D) return false

        return x == other.x && y == other.y && z == other.z
    }

    fun distance(other: Point3D): Double {
        val dx = other.x - this.x // Difference in x
        val dy = other.y - this.y // Difference in y
        val dz = other.z - this.z // Difference in z

        // Calculate the sum of squares
        val sum = dx * dx + dy * dy + dz * dz

        // Return the square root
        return sqrt(sum)
    }
}