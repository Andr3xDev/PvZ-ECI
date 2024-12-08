package GUI;

import domain.Game;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that represents the Game GUI.
 * This class is the main class of the game, it contains all the elements of the game.
 */
public class GameAPP extends JFrame {

    //** Attributes **//

    // Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Utilities
    private Game gameBack;
    private CardLayout cl;
    private GameAPP game;

    //Panel menu
    private JMenuItem optionOpen;
    private JMenuItem optionSave;

    /**
     * Constructor, creates the Game's elements and actions.
     */
    public GameAPP() {
        gameBack = new Game();
        prepareElements();
//        prepareActionsMenu();
    }

    /**
     * Prepares all elements of the Game GUI.
     * Also, determinate the properties of the application.
     * It includes the Home Screen, Select Screen, tutorial screen and game screen.
     */
    private void prepareElements() {
        // Window actions
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Window properties
        setTitle("POOB vs Zombies");
        setSize(screenSize);
        setLocationRelativeTo(null);

        // Panels
        JPanel panels = new JPanel(new CardLayout());
        HomeGUI home = new HomeGUI();
        SelectGUI select = new SelectGUI(this);
        TutorialGUI tutorial = new TutorialGUI();

        // Add Cart Interfaces

        panels.add(home, "homePanel");          // Initial menu
        panels.add(tutorial, "tutorialPanel");  // Tutorial screen
        panels.add(select, "selectPanel");      // Select screen
        cl = (CardLayout) panels.getLayout();

        // Add panels to the frame
        add(panels);
//        prepareElementsMenu();
    }
//    private JMenuBar prepareElementsMenu() {
//        JMenuBar menuBar = new JMenuBar();
//        JMenu menuFile = new JMenu("File");
//        optionOpen = new JMenuItem("Open");
//        optionSave = new JMenuItem("Save");
//        menuFile.add(optionOpen);
//        menuFile.add(optionSave);
//        return menuBar;
//    }
//    private void prepareActionsMenu() {
////        optionOpen.addActionListener(
////                new ActionListener() {
////                    public void actionPerformed(ActionEvent e) {
////                        optionOpenAction();
////                    }
////                });
//        optionSave.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        optionSaveAction();
//                    }
//                });
//
//    }
////    private void optionOpenAction() {
////        try{
////            JFileChooser fileChooser = new JFileChooser();
////            FileNameExtensionFilter filter = new FileNameExtensionFilter("Garden files", "dat");
////            fileChooser.setFileFilter(filter);
////            int returnVal = fileChooser.showOpenDialog(null);
////            if (returnVal == JFileChooser.APPROVE_OPTION) {
////                JOptionPane.showMessageDialog(null, "Open file: " + fileChooser.getSelectedFile().getName());
////                gameBack = Game.open(fileChooser.getSelectedFile().getAbsolutePath());
//////                photo.repaint();
////            }
////        }catch (Exception e) {
////            JOptionPane.showMessageDialog(null, "Error open file");
////        }
////    }
//    private void optionSaveAction() {
//        try {
//            JFileChooser fileChooser = new JFileChooser();
//            FileNameExtensionFilter filter = new FileNameExtensionFilter("Garden files", "dat");
//            fileChooser.setFileFilter(filter);
//            int returnVal = fileChooser.showSaveDialog(null);
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                JOptionPane.showMessageDialog(null, "Save file: " + fileChooser.getSelectedFile().getName());
//                gameBack.save(fileChooser.getSelectedFile().getAbsolutePath()+".garden");
//            }
//        }catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error save file");
//        }
//    }



    //* Main Method *//
    public static void main(String[] args) {
        new GameAPP().setVisible(true);
    }
}