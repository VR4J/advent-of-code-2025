package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day06Test  extends Specification {

    def "returns the grand total of the cephalopods math homework"() {
        given: "the example puzzle input"
        def input = [
            "123 328  51 64",
            " 45 64  387 23",
            "  6 98  215 314",
            "*   +   *   +  "
        ]

        when: "the grand total is calculated"
        def result = new Day06().runPartOne(input)

        then: "the result matches the example answer"
        result == 4277556
    }

    def "returns total amount of fresh ingredients"() {
        given: "the example puzzle input"
        def input = [
            "123 328  51 64",
            " 45 64  387 23",
            "  6 98  215 314",
            "*   +   *   +  "
        ]

        when: "the total amount of fresh ingredients is calculated"
        def result = new Day06().runPartTwo(input)

        then: "the result matches the example answer"
        result == 3263827
    }

}
