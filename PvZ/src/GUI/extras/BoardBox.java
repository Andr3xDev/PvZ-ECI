package GUI.extras;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class BoardBox extends JButton {

    //** Attributes **//

    private int row;
    private int column;

    // Links to the previous and next cells
    private BoardBox previous;
    private BoardBox next;

    private ArrayList<String> ocupants;
    private List<ImageIcon> overlayImages;
    private ImageIcon backgroundImage;
    private String currentPlantType;




    public BoardBox(int row, int column) {
        super();
        previous = null;
        next = null;
        this.row = row;
        this.column = column;
        currentPlantType = null;
        setContentAreaFilled(false);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setPrevious(BoardBox previous) {
        this.previous = previous;
    }

    public BoardBox getPrevious() {
        return previous;
    }

    public void setNext(BoardBox next) {
        this.next = next;
    }

    public BoardBox getNext() {
        return next;
    }


    public void addPlant(String plantType) {
        this.currentPlantType = plantType;
        /*// Map plant types to their respective image paths
        Map<String, String> plantImages = Map.of(
                "SunFlower", "images/SunFlower.png",
                "PeasShooter", "images/PeasShooter.png",
                "WallNut", "images/WallNut.png",
                "PotatoMine", "images/PotatoMine.png",
                "EciPlant", "images/EciPlant.png"
        );

        // Set the background image if the plant type exists, otherwise null
        setBackgroundImage(plantImages.getOrDefault(plantType, null));*/
    }

    public void addLawnMower(int row ){
        setBackgroundImage("images/Mower.png");
    }

    public void removePlant() {
        if (currentPlantType != null) {
            this.currentPlantType = null;
            this.backgroundImage = null;
            repaint();
        }

    }

    public void setBackgroundImage(String imagePath) {
        if (imagePath != null) {
            this.backgroundImage = new ImageIcon(imagePath);
        } else {
            System.out.println("Not found"); //! This have to be an exception
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void addZombie(String zombieType) {
        switch (zombieType) {
            case "ZombieBasic":
                setBackgroundImage("images/basic.webp");
                break;
            case "ConeheadZombie":
                setBackgroundImage("images/conehead.webp");
                break;
            case "BucketheadZombie":
                setBackgroundImage("images/buckethead.webp");
                break;
            case "FlagZombie":
                setBackgroundImage("images/flag.webp");
                break;
            default:
                setBackgroundImage(null);
        }
        repaint();
    }

}