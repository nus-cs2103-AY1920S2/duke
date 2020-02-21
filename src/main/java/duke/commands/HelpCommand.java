package duke.commands;

import duke.Ui;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class HelpCommand extends Command {
    /**
     * Constructor for the Help Command.
     */
    public HelpCommand() {

    }

    /**
     * Execute the help command.
     *
     * @param storage the Storage object
     * @param tasks   the TaskList object
     * @param ui      the Ui object
     * @return the help message detailing the commands Duke can execute
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.helpMsg();
    }
}
