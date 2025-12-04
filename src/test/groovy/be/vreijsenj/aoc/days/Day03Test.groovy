package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day03Test extends Specification {

    def "returns total sum of the maximum joltage from each bank"() {
        given: "the example puzzle input"
        def input = [
            "987654321111111",
            "811111111111119",
            "234234234234278",
            "818181911112111"
        ]

        when: "the ... is calculated"
        def result = new Day03().runPartOne(input)

        then: "the result matches the example answer"
        result == 357
    }

    def "returns total sum of the maximum joltage without static friction from each bank"() {
        given: "the example puzzle input"
        def input = [
            "987654321111111",
            "811111111111119",
            "234234234234278",
            "818181911112111"
        ]

        when: "the ... is calculated"
        def result = new Day03().runPartTwo(input)

        then: "the result matches the example answer"
        result == 3121910778619
    }

}

