import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Element{

    public Monster(Position position) {
        super(position);
    }

    public Position move(){
        Random random = new Random();
        List<Position> options = new ArrayList<>();
        options.add(new Position(position.getX()+1, position.getY()));
        options.add(new Position(position.getX()-1, position.getY()));
        options.add(new Position(position.getX(), position.getY()+1));
        options.add(new Position(position.getX(), position.getY()-1));
        int i = random.nextInt(4);
        return options.get(i);
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    public void setPosition(Position pos) {
        position = pos;
    }
}
