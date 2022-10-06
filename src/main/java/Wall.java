import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {

    private Position position;

    public Wall(int x, int y){
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), " ");
    }
}
