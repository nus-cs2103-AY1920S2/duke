package duke.command;

import duke.main.TaskList;
import duke.main.Ui;
import duke.main.Storage;

/**
 * Command that is executed when user inputs '/help'.
 */
public class HelpCommand extends Command {

    /**
     * Executes the 'help' command.
     *
     * @param tasks Task list
     * @param ui Current user interface
     * @param storage Current storage
     * @return String representation of list of commands
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }

    /**
     * Updates main function if it should exit the programme or not.
     *
     * @return True if 'bye' command is called, false otherwise. In this case, false is returned.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
