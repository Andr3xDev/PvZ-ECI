package GUI.extras;

import javax.swing.*;
import java.awt.*;

/**
 * Class to create a rounded button used over all GUI to make it look good.
 */
public final class RoundedButton extends JButton {

    // Attributes
    private final int cornerRadius;


    /**
     * Constructor to create a rounded button.
     * @param text The text of the button.
     * @param cornerRadius The corner radius of the button.
     */
    public RoundedButton(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setRolloverEnabled(false);
        setPressedIcon(null);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(252, 250, 227));
        setFont(new Font("Arial", Font.BOLD, 30));
    }


    /**
     * Method to paint the button.
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
     * Method to paint the border of the button.
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