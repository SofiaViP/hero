import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public abstract class Element {
    protected Position position;

    public Element (Position position){
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }

    public abstract void draw(TextGraphics textGraphics) throws IOException;
}
