import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {
    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Screen screen) throws IOException {
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }

    public void moveUp(){
        this.y--;
    }

    public void moveDown(){
        this.y++;
    }

    public void moveLeft(){
        this.x--;
    }

    public void moveRight(){
        this.x++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x;
    private int y;
}
