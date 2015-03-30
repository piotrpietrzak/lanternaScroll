package me.pietrzak.lanterna.atari


class Line {
    int[] bytes = []
    private final int size

    Line(int size) {
        this.size = size
        bytes = [0] * size
    }

    void setBit(int bitState) {
        bytes[0] = bytes[0] | bitState
    }

    void rotateBits() {
        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = bytes[i] << 1
            if (bytes[i] > 255) {
                overflowAt(i)
            }
        }
    }

    void overflowAt(int i) {
        bytes[i] = bytes[i] & 255
        if (i < bytes.length) {
            bytes[i + 1] = bytes[i - 1] | 1
        }
    }

    boolean getBitAt(int i) {
        return bytes[(int) Math.floor(i / 8)] >> (i % 8) & 1
    }

    @Override
    String toString() {
        String visualization = ''
        for (int i = 0; i < bytes.length * 8; i++) {
            visualization += getBitAt(i) ? '1' : '0'
        }
        return visualization
    }
}
