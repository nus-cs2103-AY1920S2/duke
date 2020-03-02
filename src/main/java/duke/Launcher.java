package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /** The main entry point of the program with GUI. */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
