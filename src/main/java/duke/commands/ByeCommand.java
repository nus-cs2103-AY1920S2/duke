package duke.commands;

import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command 'bye'.
 */
public class ByeCommand extends Command {

    /**
     * Executes 'bye' command.
     * @param taskList task list in running program.
     * @param ui ui handling running program.
     * @param storage storage handling running program.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.exitMsg();
    }

    /**
     * Compares and checks if commands are equal.
     * @param command object to compare command to.
     * @return boolean stating if this command and command are equal.
     */
    @Override
    public boolean equals(Object command) {
        return command instanceof ByeCommand;
    }
}
