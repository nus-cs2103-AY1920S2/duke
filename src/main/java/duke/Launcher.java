package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues for GUI Purposes.
 * Contains the main method that launches Duke Application.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
