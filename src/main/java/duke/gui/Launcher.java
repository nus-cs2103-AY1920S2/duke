package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main programs begins here for GUI.
     *
     * @param args user input from GUI.
     */
    public static void main(String... args) {
        Application.launch(Main.class, args);
    }
}
