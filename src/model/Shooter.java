package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Shooter extends GameFigure {
    
    public static final int UNIT_TRAVEL = 5; // per key press

    private Image launcherImage;

    public Shooter(int x, int y) {
        super(x, y);
        
        launcherImage = null;
        
        try {
            launcherImage = ImageIO.read(getClass().getResource("shooter.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(launcherImage, super.x, super.y, 30, 30, null);
    }

    @Override
    public void update() {
        // no periodic update is required
        // update is done via 'translate' when a key is pressed
    }

    public void translate(int dx, int dy) {
        super.x += dx;
        super.y += dy;
    }

}
