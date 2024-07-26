package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class SoundManager {

    public void playMoveAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playSound("/resources/sounds/move_sound.wav",-20);
    }

    public void playCheckmateSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playSound("/resources/sounds/checkmate_sound.wav",-20);
    }
    public void playDrawSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        playSound("/resources/sounds/draw_sound.wav",-20);
    }

    public void playCheckSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound("/resources/sounds/check_sound.wav",0);
    }

    public void playErrorSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound("/resources/sounds/error.wav",0);
    }

    public void playSuccessSound() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound("/resources/sounds/success.wav",0);
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
