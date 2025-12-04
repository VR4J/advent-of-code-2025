package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day04Test extends Specification {

    def "returns total rolls of paper that can be accessed bya forklift"() {
        given: "the example puzzle input"
        def input = [
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@."
        ]

        when: "the total accessible rolls of paper are calculated"
        def result = new Day04().runPartOne(input)

        then: "the result matches the example answer"
        result == 13
    }

    def "returns total sum of the maximum joltage without static friction from each bank"() {
        given: "the example puzzle input"
        def input = [
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@."
        ]

        when: "the ... is calculated"
        def result = new Day04().runPartTwo(input)

        then: "the result matches the example answer"
        result == 43
    }

}
