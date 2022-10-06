import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Hero extends Element{

    public Hero(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");

        /*
        O que raio é que estas duas linhas estão a fazer ?!
        graphics.putString(new TerminalPosition(position.getX()*2, position.getY()*2), "\\/");
        graphics.putString(new TerminalPosition(position.getX()*2, position.getY()*2), "/\\");
        */
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY());
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
