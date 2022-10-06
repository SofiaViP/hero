import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;

    private List<Wall> walls;
    private List<Coin> coins;

    private List<Monster> monsters = new ArrayList<>();

    private Hero hero;

    public Arena(int w, int h) {
        Random random = new Random();
        this.width = w;
        this.height = h;
        hero = new Hero(new Position(width / 2, height / 2));

        for (int i = 0; i < 5; i++){
            this.monsters.add(new Monster(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1)));
        }
        this.walls = createWalls();
        this.coins = createCoins();
    }

    public void verifyMonsterCollisions(Screen screen) throws IOException{
        for (Monster monster : monsters){
            if (monster.getPosition().equals(hero.getPosition())) {
                screen.close();
                System.out.print("The End");
            }
        }
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c, 0)));
            walls.add(new Wall(new Position(c, height - 1)));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(width - 1, r)));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Position pos = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            boolean val = true;
            for (int j = 0; j < i; j++) {
                if (coins.get(j).getPosition().equals(pos) || hero.getPosition().equals(pos)) {
                    val = false;
                    break;
                }
            }
            if (val) coins.add(new Coin(pos));
            else i--;
        }
        return coins;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            retrieveCoins();
        }
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        return true;
    }

    public void moveMonsters() {
        Position pos;
        for (Monster monster : monsters) {
            while (true) {
                pos = monster.move();
                if (canMonsterMove(pos)) {
                    monster.setPosition(pos);
                    break;
                }
            }
        }
    }

    private boolean canMonsterMove(Position position){
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position)) return false;
        }
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) return false;
        }
        return true;
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

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);
        hero.draw(graphics);
    }

    public void retrieveCoins() {
        int coin2remove = -1;
        for (int i = 0; i < coins.size(); i++) {
            if (hero.getPosition().equals(coins.get(i).getPosition())) {
                coin2remove = i;
                break;
            }
        }
        if (coin2remove != -1) coins.remove(coins.get(coin2remove));

    }
}
