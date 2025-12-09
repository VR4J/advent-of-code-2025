package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day09Test extends Specification {

    def "returns the largest possible area between red tiles"() {
        given: "the example puzzle input"
        def input = [
            "7,1",
            "11,1",
            "11,7",
            "9,7",
            "9,5",
            "2,5",
            "2,3",
            "7,3"
        ]

        when: "the biggest area is calculated"
        def result = new Day09().runPartOne(input)

        then: "the result matches the example answer"
        result == 50
    }

    def "returns the largest possible area containing red and green tiles"() {
        given: "the example puzzle input"
        def input = [
            "7,1",
            "11,1",
            "11,7",
            "9,7",
            "9,5",
            "2,5",
            "2,3",
            "7,3"
        ]

        when: "the largest area is calculated"
        def result = new Day09().runPartTwo(input)

        then: "the result matches the example answer"
        result == 24
    }

}
