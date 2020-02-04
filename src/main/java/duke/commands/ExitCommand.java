package duke.commands;

import duke.utilities.*;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    /**
     * Prints the Ui's exit message.
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return false to indicate that this is an ExitCommand
     */
    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) {
        ui.exitMsg();
        return false; // return false to exit
    }
}
