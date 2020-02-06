package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * The Launcher class acts as an entry point to our application
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}