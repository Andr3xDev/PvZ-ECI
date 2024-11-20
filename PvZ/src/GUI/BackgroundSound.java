package GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class BackgroundSound {
    
    public BackgroundSound(String filePath) {
        startMusic(filePath);
    }

    private void startMusic(String filePath) {
        try {
            FileInputStream is = new FileInputStream(filePath);
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            Clip ini = (Clip) AudioSystem.getLine(info);
            ini.open(ais);
            ini.start();
            ini.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
