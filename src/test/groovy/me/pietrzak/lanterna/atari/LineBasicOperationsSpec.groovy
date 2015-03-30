package me.pietrzak.lanterna.atari

import spock.lang.Specification


class LineBasicOperationsSpec extends Specification {
    def "should set bit and shift line in only one byte scope"() {
        given:
        Line line = new Line(2);
        when:
        line.setBit(1)
        line.rotateBits()
        then:
        false == line.getBitAt(0)
        true == line.getBitAt(1)
    }

    def "should deal with more complicated pattern in only one byte scope"() {
        given:
        Line line = new Line(2);
        when:
        line.setBit(1)
        line.rotateBits()
        line.rotateBits()
        line.setBit(1)
        line.rotateBits()
        then:
        false == line.getBitAt(0)
        true == line.getBitAt(1)
        false == line.getBitAt(2)
        true == line.getBitAt(3)
        false == line.getBitAt(4)
    }

    def "should overflow properly over one byte"() {
        given:
        Line line = new Line(2);
        when:
        line.setBit(1)
        for(int i=0; i<9;i++) {
            line.rotateBits()
        }
        then:
        false == line.getBitAt(8)
        true == line.getBitAt(9)
        false == line.getBitAt(10)
    }
}
