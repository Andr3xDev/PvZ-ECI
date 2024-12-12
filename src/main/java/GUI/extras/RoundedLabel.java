package GUI.extras;

import javax.swing.*;
import java.awt.*;

/**
 * Class to create a rounded label. Used to make the GUI look good and represents some elements.
 */
public class RoundedLabel extends JLabel {

    // Attributes
    private final int cornerRadius;


    /**
     * Constructor to create a rounded label.
     * @param text The text of the label.
     */
    public RoundedLabel(String text) {
        super(text);
        this.cornerRadius = 40;

        setOpaque(false);
        setFont(new Font("Arial", Font.BOLD, 15));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setForeground(Color.BLACK);
        setBackground(new Color(252, 250, 227));
    }


    /**
     * Method to paint the label.
     * @param g The graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g2d);
    }


    /**
     * Method to paint the border of the label.
     * @param g The graphics object.
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }
}