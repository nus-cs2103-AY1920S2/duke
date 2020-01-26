package commands;

import processor.DukeProcessor;
import processor.Ui;

/**
 * Command for Duke to say goodbye. Handles the disabling of the bot as well.
 */
public class CommandBye implements Command {

    /**
     * Prints a goodbye message to the UI, and also disables the bot to stop the loop.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public void execute(DukeProcessor processor, String args) {
        Ui.print("Ok see you! Hopefully I could help you in some way! :)");
        processor.disable();
    }
}
