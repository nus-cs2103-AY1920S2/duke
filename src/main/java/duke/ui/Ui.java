package duke.ui;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Provides functions for easier formatted outputs.
 */
public class Ui {
    private String bar = "    **************************************************************\n";
    private static MainWindow mainWindowController;

    public Ui() {
    }

    public static void setMainWindowController(MainWindow mainWindowController) {
        Ui.mainWindowController = mainWindowController;
    }

    /**
     * Formats any output between two bars.
     *
     * @param output Formatted output.
     */
    public void printFormattedOutput(String output) {
        System.out.println(bar + "    " + output + "\n" + bar);
    }

    /**
     * Prints given text as Duke's message in GUI.
     *
     * @param output Text for printing to GUI.
     */
    public static void printOutput(String output) {
        mainWindowController.outputText(output);
    }

    /**
     * Prints task list with indexing.
     * @param list Tasklist to be printed.
     */
    public void printList(ArrayList<Task> list) {
        System.out.print(bar);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + " " + list.get(i));
        }
        System.out.println(bar);
    }
}
