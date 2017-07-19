package controller;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import model.GameData;
import model.GameFigure;
import view.MainWindow;

public class MouseController extends MouseAdapter {

    private int px;
    private int py;
    
    @Override
    public void mousePressed(MouseEvent me) {
        px = me.getX();
        py = me.getY();
        Main.gamePanel.areaSelector = new Rectangle();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
        List<GameFigure> remove = new ArrayList<>();

        GameData.sharedDataLock.lock();           
        try {
            // i == 0; the shooter
            for (int i = 1; i < Main.gameData.figures.size(); i++) {
                if (Main.gamePanel.areaSelector.contains(
                        Main.gameData.figures.get(i).x, 
                        Main.gameData.figures.get(i).y)) {
                    remove.add(Main.gameData.figures.get(i));
                }
            }
            
            int killed = remove.size();
            Main.gameData.figures.removeAll(remove);
            int totalEnemies = Main.gameData.getTotalEnemies();
            int killedEnemies = Main.gameData.getKilledEnemies() + killed;
            Main.gameData.setKilledEnemies(killedEnemies);
            
            MainWindow.message.
                    setText("Enemy Stats: total = " + totalEnemies +
                            ", killed = " + killedEnemies + 
                            ", alive:" + (totalEnemies-killedEnemies));
        } finally {
            GameData.sharedDataLock.unlock();
        }

        Main.gamePanel.areaSelector = null;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        int dx = me.getX(); // drag point
        int dy = me.getY();
        int x, y, w, h;
        if (px < dx) {
            x = px;
            w = dx - px;
        } else {
            x = dx;
            w = px - dx;
        }
        if (py < dy) {
            y = py;
            h = dy - py;
        } else {
            y = dy;
            h = py - dy;
        }
        Main.gamePanel.areaSelector.setRect(x, y, w, h);
    }

}
