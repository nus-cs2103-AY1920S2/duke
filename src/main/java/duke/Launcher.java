package duke;

import javafx.application.Application;

/**
 * This class launches the GUI frontend of Duke.
 * This class is needed to avoid classpath issues
 * (since JavaFX expects the `Application` subclass to be available as a module, instead of a .jar)
 */
public class Launcher {
    /**
     * Constructs and runs an instance of the Duke application.
     * @see <a href="https://github.com/javafxports/openjdk-jfx/issues/236#issuecomment-426583174">
     *  Include dependencies in jar #236
     * </a>
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
