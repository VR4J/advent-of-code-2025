package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day01Test extends Specification {

    def "returns something 1"() {
        given: "the example puzzle input"
        def input = [ ]

        when: "the something is calculated"
        def result = new Day01().runPartOne(input)

        then: "the result matches the example answer"
        result == 1
    }

    def "returns something 2"() {
        given: "the example puzzle input"
        def input = [ ]

        when: "the something is calculated"
        def result = new Day01().runPartTwo(input)

        then: "the result matches the example answer"
        result == 1
    }

}
