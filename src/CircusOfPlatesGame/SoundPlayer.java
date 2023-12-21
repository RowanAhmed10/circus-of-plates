package CircusOfPlatesGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {
    
    static Clip background;
    
    public static void playSound(String musicFileName) {
        try {
            File soundFile = new File(musicFileName);

            if (!soundFile.exists()) {
                System.out.println("File not found: " + musicFileName);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      public static void playSoundBackground(String musicFileName) {
        try {
            File soundFile = new File(musicFileName);
            if (!soundFile.exists()) {
                System.out.println("File not found: " + musicFileName);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            background= AudioSystem.getClip();
            background.open(audio);
            background.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void stopSound(String musicFileName){
       background.stop();
    }
}