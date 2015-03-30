package me.pietrzak.lanterna

import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.ScreenTextGraphics
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.Terminal
import me.pietrzak.lanterna.atari.AtariFont

class AtariFontScroll {
    AtariFont atariFont = new AtariFont()

    public void scroll(Terminal terminal, Screen screen, ScreenTextGraphics writer) {
        displayMessageInRightBottomCorner(writer, screen, "Press ESC")

        String message = "FOLLOW MY BLOG : HTTP://PIETRZAK.ME *** FORK MY PROJECTS ON GITHUB : HTTPS://GITHUB.COM/PIOTRPIETRZAK *** "
        int offset = 1
        while(offset++) {
            for (int i = 0; i < screen.terminalSize.getColumns(); i++) {
                for (int j = 0; j < 8; j++) {
                    writer.putString(i, j + (int) Math.floor(screen.terminalSize.getRows() / 2) - 4, computeBit(message, i + offset, j) ? "█" : " ")
                }
            }
            screen.refresh()
            KeyStroke key = terminal.pollInput()
            if (key != null && key.getKeyType() == KeyType.Escape) {
                break
            }
            Thread.sleep(30)
        }
    }

    def computeBit(String message, int i, int j) {
        return atariFont.bitForCharAtXY(message[(int) Math.floor(i / 8) % message.length()], 7 - i % 8, j)
    }

    private TextGraphics displayMessageInRightBottomCorner(ScreenTextGraphics writer, TerminalScreen screen, String message) {
        writer.putString(screen.terminalSize.getColumns() - message.length(), screen.terminalSize.getRows() - 1, message)
    }
}
