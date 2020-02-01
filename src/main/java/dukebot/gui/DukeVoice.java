package dukebot.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

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
    private final Media dukeSound;

    DukeVoice() {
        dukeSound = null;
    }

    DukeVoice(String fileName) {
        dukeSound = new Media(new File("src/main/resources/sound/" + fileName + ".wav").toURI().toString());
        // dukeVoicePlayer = new MediaPlayer(sound);
    }

    /**
     * Randomise voice to play for more excitement.
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

    // public MediaPlayer getDukeVoicePlayer() {
    //     return dukeVoicePlayer;
    // }

    /**
     * Plays corresponding voice.
     */
    public void playVoice() {
        if (this != NO_VOICE) {
            new MediaPlayer(dukeSound).play();
        }
    }
}
