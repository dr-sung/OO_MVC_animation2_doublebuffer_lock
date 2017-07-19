package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Shooter;

public class KeyController implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        
        Shooter shooter = (Shooter) Main.gameData.figures.get(0);
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                shooter.translate(-Shooter.UNIT_TRAVEL, 0);
                break;
            case KeyEvent.VK_RIGHT:
                shooter.translate(Shooter.UNIT_TRAVEL, 0);
                break;
            case KeyEvent.VK_UP:
                shooter.translate(0, -Shooter.UNIT_TRAVEL);
                break;
            case KeyEvent.VK_DOWN:
                shooter.translate(0, Shooter.UNIT_TRAVEL);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
