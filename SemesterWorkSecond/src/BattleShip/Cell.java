package BattleShip;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    public int x, y;
    public Ship ship = null;
    public boolean wasShot = false;
    public boolean enemyBoard;


    public Cell(int x, int y,boolean enemyBoard) {
        super(25, 25);
        this.x = x;
        this.y = y;
        this.enemyBoard = enemyBoard;
        setFill(Color.LIGHTGRAY);
        setStroke(Color.BLACK);
    }

    public boolean shoot() {
        wasShot = true;
        setFill(Color.BLACK);

        if (ship != null) {
            ship.hit();
            setFill(Color.RED);
            return true;
        }

        return false;
    }
}
