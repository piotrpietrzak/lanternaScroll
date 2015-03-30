package me.pietrzak.lanterna.atari

import spock.lang.Specification

class AtariFontSpec extends Specification {
    def "should display letter dot by dot"(x, y, result) {
        given:
        AtariFont atariFont = new AtariFont();
        when:
        boolean state = atariFont.bitForCharAtXY('A', x, y)
        then:
        state == result
        where:
        x  | y | result
        0  | 0 | false
        3  | 0 | false
        2  | 1 | false
        3  | 1 | true
        4  | 1 | true
        5  | 1 | false
        6  | 1 | false
        7  | 1 | false
        11 | 1 | false
        0  | 3 | false
        1  | 3 | true
        2  | 3 | true
        3  | 3 | false
        4  | 3 | false
        5  | 3 | true
        6  | 3 | true
        7  | 3 | false


    }
}
