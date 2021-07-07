package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Handles unknown commands not understood by the application.
 */
public class UnknownCommand extends Command {

    /**
     * Execute the UnknownCommand. Handles commands not understood by Parser.
     *
     * @param tasks Unused.
     * @param ui This is to interact with the user interface, printing Unknown command message.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "I do not understand what you mean!!! :(";
        ui.printMsg(msg);
        return msg;
    }

    /**
     * UnknownCommand does not cause the programme to exit.
     *
     * @return boolean false since not ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
