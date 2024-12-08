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

    public void addLawnMower(){
        setBackgroundImage("PvZ/assets/Others/mower.png");
    }

    public void remove() {
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
            case "basic":
                setBackgroundImage("PvZ/assets/zombies/basic.png");
                break;
            case "conehead":
                setBackgroundImage("PvZ/assets/zombies/conehead.png");
                break;
            case "buckethead":
                setBackgroundImage("PvZ/assets/zombies/buckethead.png");
                break;
            case "brainstein":
                setBackgroundImage("PvZ/assets/zombies/brainstein.png");
                break;
            case "ecizombie":
                setBackgroundImage("PvZ/assets/zombies/ecizombie.png");
                break;
            default:
                setBackgroundImage(null);
        }
        repaint();
    }

    public void addPlant(String plantType) {
        switch (plantType) {
            case "sunflower":
                setBackgroundImage("PvZ/assets/plants/sunflower.png");
                break;
            case "peashooter":
                setBackgroundImage("PvZ/assets/plants/peashooter.png");
                break;
            case "wallnut":
                setBackgroundImage("PvZ/assets/plants/wallNut.png");
                break;
            case "potatomine":
                setBackgroundImage("PvZ/assets/plants/potatomine.png");
                break;
            case "eciplant":
                setBackgroundImage("PvZ/assets/plants/eciplant.png");
                break;
            default:
                setBackgroundImage(null);
        }
        repaint();
    }


    public void clear() {
        this.setIcon(null);
    }
}