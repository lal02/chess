package sound;

import utility.LoggingUtility;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class SoundManager {

    public void playMoveAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playSound("/sounds/move_sound.wav",-20);
        LoggingUtility.getLogger().info("played move_sound.wav");
    }

    public void playCheckmateSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playSound("/sounds/checkmate_sound.wav",-20);
        LoggingUtility.getLogger().info("played checkmate_sound.wav");
    }
    public void playDrawSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playSound("/sounds/draw_sound.wav",-20);
        LoggingUtility.getLogger().info("played draw_sound.wav");
    }

    public void playCheckSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound("/sounds/check_sound.wav",0);
        LoggingUtility.getLogger().info("played check_sound.wav");
    }

    public void playErrorSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound("/sounds/error.wav",0);
        LoggingUtility.getLogger().info("played error_sound.wav");

    }

    public void playSuccessSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound("/sounds/success.wav",0);
        LoggingUtility.getLogger().info("played success_sound.wav");

    }

    private void playSound(String path, int volumeAdjustment) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volumeAdjustment);
        clip.start();
    }

}
