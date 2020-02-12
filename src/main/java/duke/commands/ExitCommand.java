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
     * @return String bye message
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.exitMsg();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
