package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the Duke Application.
     *
     * @param args standard argument.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}