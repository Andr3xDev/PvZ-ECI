package GUI.extras;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class BoardBox extends JButton {

    //** Attributes **//

    private final int row;
    private final int column;

    // Links to the previous and next cells

    private ArrayList<String> occupants;
    private List<ImageIcon> overlayImages;
    private ImageIcon backgroundImage;
    private String currentPlantType;




    public BoardBox(int row, int column) {
        super();
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

    /**
     * Set the initial mower image to the cell
     */
    public void addLawnMower(){
        setBackgroundImage("src/main/resources/Others/mower.png");
        repaint();
    }

    public void remove() {
        if (currentPlantType != null) {
            this.currentPlantType = null;
            this.backgroundImage = null;
            repaint();
        }
    }

    /**
     * Set the background image of the cell to the given image
     * @param imagePath the path to the image
     */
    public void setBackgroundImage(String imagePath) {
        if (imagePath != null) {
            this.backgroundImage = new ImageIcon(imagePath);
        }
        repaint();
    }

    /**
     * Paint the component with the background image
     * @param g the Graphics object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Add a zombie to the cell with the given zombie type
     * @param zombieType the type of zombie to add
     */
    public void addZombie(String zombieType) {
        switch (zombieType) {
            case "basic":
                setBackgroundImage("src/main/resources/zombies/basic.png");
                break;
            case "conehead":
                setBackgroundImage("src/main/resources/zombies/conehead.png");
                break;
            case "buckethead":
                setBackgroundImage("src/main/resources/zombies/buckethead.png");
                break;
            case "brainstein":
                setBackgroundImage("src/main/resources/zombies/brainstein.png");
                break;
            case "ecizombie":
                setBackgroundImage("src/main/resources/zombies/ecizombie.png");
                break;
            default:
                setBackgroundImage(null);
        }
        repaint();
    }

    /**
     * Add a plant to the cell with the given plant type
     * @param plantType the type of plant to add
     */
    public void addPlant(String plantType) {
        switch (plantType) {
            case "sunflower":
                setBackgroundImage("src/main/resources/plants/sunflower.png");
                break;
            case "peashooter":
                setBackgroundImage("src/main/resources/plants/peashooter.png");
                break;
            case "wallnut":
                setBackgroundImage("src/main/resources/plants/wallnut.png");
                break;
            case "potatomine":
                setBackgroundImage("src/main/resources/plants/potatomine.png");
                break;
            case "eciplant":
                setBackgroundImage("src/main/resources/plants/eciplant.png");
                break;
            default:
                setBackgroundImage(null);
        }
        repaint();
    }

    /**
     * Remove the unit from the box
     */
    public void clear() {
        this.setBackgroundImage("src/main/resources/plants/null.png");
        System.out.println(backgroundImage);
        repaint();
    }
}