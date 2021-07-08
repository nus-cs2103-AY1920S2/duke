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
        if (args.length >= 1 && args[0].equals("text")) {
            Duke duke = new Duke(false);
            duke.run();
        } else {
            Application.launch(Main.class, args);
        }
    }
}