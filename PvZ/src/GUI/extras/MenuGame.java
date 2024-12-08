package GUI.extras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuGame extends JFrame {

    // Buttons
    private RoundedButton continueButton;
    private RoundedButton saveButton;
    private RoundedButton loadButton;
    private RoundedButton exitButton;

    public MenuGame() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setSize(200, 300);

        continueButton = new RoundedButton("CONTINUE", 30);
        saveButton = new RoundedButton("SAVE GAME", 30);
        loadButton = new RoundedButton("LOAD GAME", 30);
        exitButton = new RoundedButton("EXIT", 30);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 1, 0, 10));

        buttonsPanel.add(continueButton, BorderLayout.CENTER);
        buttonsPanel.add(saveButton, BorderLayout.CENTER);
        buttonsPanel.add(loadButton, BorderLayout.CENTER);
        buttonsPanel.add(exitButton, BorderLayout.CENTER);

        revalidate();
        repaint();
    }


    private void prepareActions() {

        // Continue button
        continueButton.addActionListener(_ -> setVisible(false));

        // Save button
        saveButton.addActionListener(_ -> {
            System.out.println("Save");
        });

        // Load button
        loadButton.addActionListener(_ -> {
            System.out.println("Load");
        });

        // Exit button
        exitButton.addActionListener(_ -> {
            System.out.println("Exit");
        });
    }
}