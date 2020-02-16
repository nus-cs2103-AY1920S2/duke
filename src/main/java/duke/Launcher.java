package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    //@@author j-lum
    //@@author damithc
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}