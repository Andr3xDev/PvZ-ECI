package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartScreen extends JFrame{
    private JPanel panel1;
    private BufferedImage backgroundImage;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public StartScreen() {
        prepareElements();
        prepareActions();
        setBackgroundImage();
    }

    private void prepareElements() {
        panel1 = new JPanel();
        button1 = new JButton("PLAY");
        button2 = new JButton("TUTORIAL");
        button3 = new JButton("EXIT");
    }

    private void prepareActions() {
        JFrame frame = new JFrame("Plants vs Zombies");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setBackgroundImage(String root) {
        try {
            backgroundImage = ImageIO.read(new File());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
