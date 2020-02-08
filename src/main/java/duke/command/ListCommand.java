package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents a list command.
 * The command deals with listing the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Returns the messages that show the list of tasks.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @return The messages that show the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.generateListMessage(tasks.getTasks());
    }


}
