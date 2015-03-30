package me.pietrzak.lanterna
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.ScreenTextGraphics
import com.googlecode.lanterna.terminal.Terminal
import groovy.util.logging.Slf4j

@Slf4j
class SimpleScroll {
    public static final SCROLL =
            [$/ ____  _       _          ____  _      _                 _       /$,
             $/|  _ \(_) ___ | |_ _ __  |  _ \(_) ___| |_ _ __ ______ _| | __   /$,
             $/| |_) | |/ _ \| __| '__| | |_) | |/ _ \ __| '__|_  / _` | |/ /   /$,
             $/|  __/| | (_) | |_| |    |  __/| |  __/ |_| |   / / (_| |   <    /$,
             $/|_|   |_|\___/ \__|_|    |_|   |_|\___|\__|_|  /___\__,_|_|\_\   /$]
    public static final WINDOW_SIZE = 30

    public void scroll(Terminal terminal, Screen screen, ScreenTextGraphics writer) {
        for (int i = 0; i < SCROLL[0].length() + 2; i++) {
            for (int j = 0; j < 5; j++) {
                String currentText = (SCROLL[j] + SCROLL[j]).substring(i, i + WINDOW_SIZE)
                writer.setForegroundColor(TextColor.Indexed.fromRGB(255, j * 30, 0))
                writer.putString((int) Math.floor(screen.terminalSize.columns/2)-15, (int) Math.floor(screen.terminalSize.rows/2)-3 + j, currentText)
            }
            if (i > SCROLL[0].length()) {
                i = 1
            }
            screen.refresh()
            KeyStroke key = terminal.pollInput();
            Thread.sleep(50)
            if (key != null && key.getKeyType() == KeyType.Escape) {
                break
            }
        }
    }


}
