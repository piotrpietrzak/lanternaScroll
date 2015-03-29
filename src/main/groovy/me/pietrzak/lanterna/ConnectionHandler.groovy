package me.pietrzak.lanterna

import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.ScreenTextGraphics
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.Terminal

class ConnectionHandler {
    void handleNewConnection(final Terminal terminal, Closure closure) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Screen screen = new TerminalScreen(terminal)
                    screen.startScreen()
                    TextGraphics writer = new ScreenTextGraphics(screen);
                    new SimpleScroll().scroll(terminal, screen, writer);
                }
                catch (IOException e) {
                    closure "Error during handle connection", e
                }
                finally {
                    try {
                        terminal.close();
                    }
                    catch (IOException e) {
                        closure "Can't close terminal", e
                    }
                }
            }


        }.start();
    }
}

