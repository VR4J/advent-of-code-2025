package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day05Test extends Specification {

    def "returns total amount of fresh ingredients available"() {
        given: "the example puzzle input"
        def input = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """

        when: "the total amount of fresh ingredients are calculated"
        def result = new Day05().runPartOne(input)

        then: "the result matches the example answer"
        result == 3
    }

    def "returns total amount of fresh ingredients"() {
        given: "the example puzzle input"
        def input = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """

        when: "the total amount of fresh ingredients is calculated"
        def result = new Day05().runPartTwo(input)

        then: "the result matches the example answer"
        result == 14
    }

}
