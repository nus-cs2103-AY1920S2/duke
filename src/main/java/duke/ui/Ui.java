package duke.ui;

import duke.controllers.MainWindow;

/**
 * Provides functions for easier formatted outputs.
 */
public class Ui {
    private static MainWindow mainWindowController;

    public Ui(MainWindow mainWindowController) {
        Ui.mainWindowController = mainWindowController;
    }

    public Ui() {
    }

    /**
     * Prints given text as Duke's message in GUI.
     *
     * @param output Text for printing to GUI.
     */
    public static void printOutput(String output) {
        mainWindowController.outputText(output);
    }
}
