package me.pietrzak.lanterna.atari


class AtariScrollModel {
    Line[] lines = []
    String text
    int offset

    AtariScrollModel(String text, int scrollLength) {
        this.text = text
        for(int i=0;i<8;i++) {
            lines[i] = new Line(scrollLength)
        }
    }

    void move(int offset) {
        this.offset++
    }




}
