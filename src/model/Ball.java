package model;

import view.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends GameFigure {

    private final int radius;
    private final Color color;
    private int dx = 3;
    private int dy = 3;

    public Ball(int x, int y, int radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        // drawOval for drawing outline only
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public void update() {
        // ball bounces on the wall
        super.x += dx;
        super.y += dy;

        if (super.x + radius > GamePanel.width) {
            dx = -dx;
            super.x = GamePanel.width - radius;
        } else if (super.x - radius < 0) {
            dx = -dx;
            super.x = radius;
        }

        if (super.y + radius > GamePanel.height) {
            dy = -dy;
            super.y = GamePanel.height - radius;
        } else if (super.y - radius < 0) {
            dy = -dy;
            super.y = radius;
        }
    }
}
