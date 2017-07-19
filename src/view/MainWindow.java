package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

    public static JTextField message;

    public static JButton addButton;
    public static JButton quitButton;

    public void initialize() {

        Container c = getContentPane();

        message = new JTextField("Shoot (not implemented yet) or Drag to kill enemies");
        message.setFont(new Font("Courier New", Font.BOLD, 16));
        message.setEditable(false);
        c.add(message, "North");

        c.add(Main.gamePanel, "Center");

        JPanel southPanel = new JPanel();
        addButton = new JButton("Add 5000");
        southPanel.add(addButton);
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        ButtonListener buttonListener = new ButtonListener();
        addButton.addActionListener(buttonListener);
        quitButton.addActionListener(buttonListener);

        MouseController mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);
        Main.gamePanel.addMouseMotionListener(mouseController);

        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        
        // have ONLY ONE Component "true", the rest must be "false"
        Main.gamePanel.setFocusable(true);
        addButton.setFocusable(false);
        quitButton.setFocusable(false);
        message.setFocusable(false);
    }

}
