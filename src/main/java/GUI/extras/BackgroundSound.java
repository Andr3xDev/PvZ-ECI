package GUI.extras;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * Class to set a background sound to the game.
 */
public class BackgroundSound {

    /**
     * Constructor to set the background sound to the game.
     * @param filePath The path of the sound file.
     */
    public BackgroundSound(String filePath) {
        startMusic(filePath);
    }


    /**
     * Method to start the background sound.
     * @param filePath The path of the sound file.
     */
    private void startMusic(String filePath) {
        try {
            FileInputStream is = new FileInputStream(filePath);
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            Clip ini = (Clip) AudioSystem.getLine(info);

            ini.open(ais);

            FloatControl volumeControl = (FloatControl) ini.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-10.0f);

            ini.start();
            ini.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}