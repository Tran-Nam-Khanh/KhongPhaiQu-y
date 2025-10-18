package application;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class SoundManager {
    private MediaPlayer backgroundMusicPlayer;

    // @TODO: Thay thế "background.mp3" bằng tên file nhạc nền thực tế
    private static final String BACKGROUND_MUSIC_FILE = "/ui/assets/sound/background.mp3";

    public void playBackgroundMusic() {
        try {
            URL resource = getClass().getResource(BACKGROUND_MUSIC_FILE);
            Media media = new Media(resource.toString());
            backgroundMusicPlayer = new MediaPlayer(media);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Lặp lại vô hạn
            backgroundMusicPlayer.play();
        } catch (Exception e) {
            System.err.println("Couldn't load background music: " + e.getMessage());
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    public void playSoundEffect(String soundFile) {
        try {
            URL resource = getClass().getResource("/ui/assets/sound/" + soundFile);
            AudioClip clip = new AudioClip(resource.toString());
            clip.play();
        } catch (Exception e) {
            System.err.println("Couldn't load sound effect: " + soundFile);
        }
    }
}
