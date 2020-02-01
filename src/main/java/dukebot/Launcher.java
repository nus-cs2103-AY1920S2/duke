package dukebot;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the GUI.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}