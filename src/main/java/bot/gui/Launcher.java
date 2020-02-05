package bot.gui;

import javafx.application.Application;

/**
 * A minimal class that starts the JavaFX GUI,
 * to avoid classpath issues
 */
public class Launcher {
    /**
     * Starts the JavaFX application, opening
     * a new window for the GUI
     *
     * @param args Command line arguments
     */
    public static void start(String[] args) {
        Application.launch(Main.class, args);
    }
}
