package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day02Test extends Specification {

    def "returns total sum of invalid product ids"() {
        given: "the example puzzle input"
        def input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

        when: "the sum of all invalid ids is calculated"
        def result = new Day02().runPartOne(input)

        then: "the result matches the example answer"
        result == 1227775554
    }

    def "returns total sum of invalid product ids"() {
        given: "the example puzzle input"
        def input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

        when: "the sum of all invalid ids is calculated"
        def result = new Day02().runPartTwo(input)

        then: "the result matches the example answer"
        result == 4174379265
    }

}
