import com.googlecode.lanterna.terminal.ansi.TelnetTerminal
import com.googlecode.lanterna.terminal.ansi.TelnetTerminalServer
import groovy.util.logging.Slf4j
import me.pietrzak.lanterna.ConnectionHandler
import me.pietrzak.lanterna.SimpleScroll

import java.nio.charset.Charset

@Slf4j
class Scroll {

    public static void main(String[] args) throws IOException {
        TelnetTerminalServer server = new TelnetTerminalServer(1024, Charset.forName("utf-8"));
        log.info "Connect to host: ${server.serverSocket.inetAddress} with telnet on port ${server.serverSocket.localPort}"

        while (true) {
            TelnetTerminal telnetTerminal = server.acceptConnection();
            if (telnetTerminal != null) {
                log.info "New client has arrived ${telnetTerminal.remoteSocketAddress}"
                new ConnectionHandler().handleNewConnection(telnetTerminal, {
                    String message, Exception e ->
                        log.error(message,e)
                })
            }
        }
    }



}