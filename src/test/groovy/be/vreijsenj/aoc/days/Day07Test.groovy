package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day07Test extends Specification {

    def "returns the total amounts of split tachyon beams"() {
        given: "the example puzzle input"
        def input = [
            ".......S.......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".....^.^.^.....",
            "...............",
            "....^.^...^....",
            "...............",
            "...^.^...^.^...",
            "...............",
            "..^...^.....^..",
            "...............",
            ".^.^.^.^.^...^.",
            "..............."
        ]

        when: "the total splits are calculated"
        def result = new Day07().runPartOne(input)

        then: "the result matches the example answer"
        result == 21
    }

    def "returns the total routes possible"() {
        given: "the example puzzle input"
        def input = [
                ".......S.......",
                "...............",
                ".......^.......",
                "...............",
                "......^.^......",
                "...............",
                ".....^.^.^.....",
                "...............",
                "....^.^...^....",
                "...............",
                "...^.^...^.^...",
                "...............",
                "..^...^.....^..",
                "...............",
                ".^.^.^.^.^...^.",
                "..............."
        ]

        when: "the total routes are calculated"
        def result = new Day07().runPartTwo(input)

        then: "the result matches the example answer"
        result == 40
    }

}
