package duke;

import javafx.application.Application;

/**
 * Launcher class for duke.
 */
public class Launcher {
    /**
     * Main method that runs the program.
     *
     * @param args takes in a string array of argument for the program.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("nogui")) {
            new Duke().runUntilExit();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
