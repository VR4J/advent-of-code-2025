package be.vreijsenj.aoc.days

import spock.lang.Specification

class Day08Test extends Specification {

    def "returns the sizes of the three largest circuits"() {
        given: "the example puzzle input"
        def input = [
            "162,817,812",
            "57,618,57",
            "906,360,560",
            "592,479,940",
            "352,342,300",
            "466,668,158",
            "542,29,236",
            "431,825,988",
            "739,650,466",
            "52,470,668",
            "216,146,977",
            "819,987,18",
            "117,168,530",
            "805,96,715",
            "346,949,466",
            "970,615,88",
            "941,993,340",
            "862,61,35",
            "984,92,344",
            "425,690,689"
        ]

        when: "the total sizes ares multiplied"
        def result = new Day08().runPartOne(input)

        then: "the result matches the example answer"
        result == 40
    }

    def "returns the length of the extension cable necessary"() {
        given: "the example puzzle input"
        def input = [
            "162,817,812",
            "57,618,57",
            "906,360,560",
            "592,479,940",
            "352,342,300",
            "466,668,158",
            "542,29,236",
            "431,825,988",
            "739,650,466",
            "52,470,668",
            "216,146,977",
            "819,987,18",
            "117,168,530",
            "805,96,715",
            "346,949,466",
            "970,615,88",
            "941,993,340",
            "862,61,35",
            "984,92,344",
            "425,690,689"
        ]

        when: "the total length is calculated"
        def result = new Day08().runPartTwo(input)

        then: "the result matches the example answer"
        result == 25272
    }

}
