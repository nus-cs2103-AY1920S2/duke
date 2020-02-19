package duke;

import javafx.application.Application;

import java.util.Arrays;
import java.util.List;

/**
 * The {@code Launcher} class is the entry point of the Duchess program.
 * When starting up the program using the command line, use the flag
 * "-mode console" to run the application in console mode. Else it will
 * default to "-mode gui" by default.
 */
public class Launcher {
    /**
     * Starts the Duchess program.
     *
     * @param args Settings for the app.
     */
    public static void main(String[] args) {
        // Solution below adapted from https://stackoverflow.com/a/1254338
        final List<String> arguments = Arrays.asList(args);
        final int modeIndex = arguments.indexOf("-mode");
        final String mode = modeIndex == -1 ? "gui" : arguments.get(modeIndex + 1);
        if ("console".equals(mode.toLowerCase())) {
            runConsole();
        } else if ("gui".equals(mode.toLowerCase())) {
            Application.launch(Main.class, args);
        } else {
            System.err.println("Bad mode: " + mode);
        }
    }

    private static void runConsole() {
        Duke duchess = new Duke("data/tasks.json");
        duchess.run();
    }
}
