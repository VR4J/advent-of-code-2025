package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day01Test extends Specification {

    def "returns total ..."() {
        given: "the example puzzle input"
        def input = [ ]

        when: "the ... is calculated"
        def result = new Day01().runPartOne(input)

        then: "the result matches the example answer"
        result == 1
    }

    def "returns total ..."() {
        given: "the example puzzle input"
        def input = [ ]

        when: "the ... is calculated"
        def result = new Day01().runPartTwo(input)

        then: "the result matches the example answer"
        result == 1
    }

}
