import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    Screen screen;

    private int x = 10;
    private int y = 10;

    private void draw() throws IOException{
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')
                [0]);
        screen.refresh();
    }

    public void run(){
        try{
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            while (true) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.EOF) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void processKey(KeyStroke key) throws IOException {

        switch (key.getKeyType()){
            case ArrowUp:
                y--;
                break;
            case ArrowDown:
                y++;
                break;
            case ArrowLeft:
                x--;
                break;
            case ArrowRight:
                x++;
                break;
            case Character:
                if (key.getCharacter() == 'q') screen.close();
        }
    }
}



