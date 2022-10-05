import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;

    Hero hero = new Hero(new Position(10, 10));

    public Arena(int w, int h){
        this.width = w;
        this.height = h;
    }


    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        return (position.getX() >= 0 && position.getX() < this.width && position.getY() >= 0 && position.getY() < this.height);
    }

    public void processKey(KeyStroke key, Screen screen) throws IOException {

        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case Character:
                if (key.getCharacter() == 'q') screen.close();
        }
    }

    public void draw(Screen screen) throws IOException {
        hero.draw(screen);
    }
}
