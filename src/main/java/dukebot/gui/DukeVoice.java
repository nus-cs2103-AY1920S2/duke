package dukebot.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.Random;

/**
 * Class to manage voice acting.
 */
public enum DukeVoice {
    ACTUALLY("02-actually"),
    GOODBYE("02-goodbye"),
    HELLO("02-hello"),
    HEY("02-hey"),
    WHAT_YOU_LIKE("02-is_this_what_you_like"),
    LAUGHTER("02-laughter"),
    THING_YOURE_INTO("02-oh_is_this_the_kinda_thing_youre_into"),
    OKAY("02-okay"),
    WHAT("02-what"),
    NO_VOICE();

    private static final Random generator = new Random();
    private Media dukeSound;

    DukeVoice() {
        dukeSound = null;
    }

    DukeVoice(String fileName) {
        try {
            String uri = DukeVoice.class.getResource("/sound/" + fileName + ".wav").toURI().toString();
            dukeSound = new Media(uri);
        } catch (URISyntaxException | MediaException e) {
            dukeSound = null;
            e.printStackTrace();
        }
    }

    /**
     * Randomise voice to play for more excitement.
     *
     * @param hasVoice Returns NO_VOICE if set to false.
     * @param dukeVoices Voices to use.
     * @return Voice to use.
     */
    public static DukeVoice randomVoice(boolean hasVoice, DukeVoice... dukeVoices) {
        if (hasVoice) {
            int randomIndex = generator.nextInt(dukeVoices.length + 1);
            if (randomIndex < dukeVoices.length) {
                return dukeVoices[randomIndex];
            }
        }
        return NO_VOICE;
    }

    /**
     * Plays corresponding voice.
     */
    public void playVoice() {
        if (dukeSound != null) {
            new MediaPlayer(dukeSound).play();
        }
    }
}
