package GUI.extras;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class Sprite {

    private BufferedImage image;
    private int x, y, width, height;
    private boolean visible;

    public Sprite(int x, int y, boolean visible) {
        this.x = x;
        this.y = y;
        this.visible = visible;
    }

    public Sprite(int x, int y, boolean visible, int width, int height){
        this(x, y, visible);
        this.width = width;
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisible(boolean v) {
        this.visible = v;
    }

    public void setRoot(String root) {
        try {
            image = ImageIO.read(new File(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int isVisible() {
        return x;
    }

    public void paint(Graphics2D g) {
        if (visible)
            if(width==0 && height == 0)
                g.drawImage(image, x, y, null);
            else
                g.drawImage(image, x, y, width, height, null);
    }
}