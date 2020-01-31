package commands;

import processor.DukeProcessor;
import processor.Ui;

/**
 * An invalid command, which is created and executed when the user's input does not match any available commands.
 */
public class CommandInvalid implements Command {
    /**
     * Apologises for not being able to process their command.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public void execute(DukeProcessor processor, String args) {
        Ui.print("Sorry, but I don't think you've entered a valid command! :(");
    }
}
