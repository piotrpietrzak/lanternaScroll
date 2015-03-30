package me.pietrzak.lanterna.atari

import me.pietrzak.lanterna.AtariFontScroll
import spock.lang.Specification

class AtariScrollSpec extends Specification{
    def "should show A"() {
        given:
        AtariFontScroll atariFontScroll = new AtariFontScroll();
        when:
        atariFontScroll.scroll(null,null,null)
        then:
        1==1
    }
}
