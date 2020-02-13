import javafx.application.Application;

/*
 * Launcher
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 14 Feb 2020
 *
 */

/**
 * Launcher class launches the Driver class to open the GUI.
 * @author Mario Lorenzo
 */

public class Launcher {
    /**
     * Runs the GUI.
     * @param args The argument input.
     */
    public static void main(String[] args) {
        Application.launch(Driver.class, args);
    }
}
