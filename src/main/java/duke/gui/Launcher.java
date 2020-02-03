package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Used for Duke GUI.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
