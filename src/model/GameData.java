package model;

import controller.Main;
import view.GamePanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameData {

    private final int RADIUS = 6;
    public final List<GameFigure> figures;

    private int totalEnemies = 0;
    private int killedEnemies = 0;
    
    public static final Lock sharedDataLock = new ReentrantLock();

    public GameData() {
        
        figures = Collections.synchronizedList(new ArrayList<>());
        //figures = new CopyOnWriteArrayList<>();

        // GamePanel.width, height are known when rendered. Thus, at this moment,
        // we cannot use GamePanel.width and height.
        figures.add(
                new Shooter(Main.WIN_WIDTH / 2, Main.WIN_HEIGHT - 130));
    }

    public void add(int n) {

        totalEnemies += n;

        sharedDataLock.lock();
        try {
            for (int i = 0; i < n; i++) {
                float red = (float) Math.random();
                float green = (float) Math.random();
                float blue = (float) Math.random();
                // adjust if too dark since the background is black
                if (red < 0.5) {
                    red += 0.2;
                }
                if (green < 0.5) {
                    green += 0.2;
                }
                if (blue < 0.5) {
                    blue += 0.2;
                }
                figures.add(new Ball(
                        (int) (Math.random() * GamePanel.width),
                        (int) (Math.random() * GamePanel.height),
                        RADIUS,
                        new Color(red, green, blue)));
            }
        } finally {
            sharedDataLock.unlock();
        }
    }

    public void update() {
        sharedDataLock.lock();
        try {
            for (GameFigure f : figures) {
                f.update();
            }
        } finally {
            sharedDataLock.unlock();
        }
    }

    public int getTotalEnemies() {
        return totalEnemies;
    }

    public void setTotalEnemies(int totalEnemies) {
        this.totalEnemies = totalEnemies;
    }

    public int getKilledEnemies() {
        return killedEnemies;
    }

    public void setKilledEnemies(int killedEnemies) {
        this.killedEnemies = killedEnemies;
    }

}
