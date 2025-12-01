package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day01Test extends Specification {

    def "returns total number of times the dial is at 0"() {
        given: "the example puzzle input"
        def input = [
            "L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82"
        ]

        when: "the total number is calculated"
        def result = new Day01().runPartOne(input)

        then: "the result matches the example answer"
        result == 3
    }

    def "returns total number of clicks on number 0"() {
        given: "the example puzzle input"
        def input = [
                "L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"
        ]

        when: "the total clicks is calculated"
        def result = new Day01().runPartTwo(input)

        then: "the result matches the example answer"
        result == 6
    }

}
