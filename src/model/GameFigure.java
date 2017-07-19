package model;

import java.awt.Graphics2D;

public abstract class GameFigure {

    // public: for a faster access
    public int x; 
    public int y;

    public GameFigure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics2D g);

    public abstract void update();
}
