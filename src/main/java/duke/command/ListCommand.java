package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a list command.
 * The command deals with listing the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {}

    /**
     * Print the list of tasks.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showList(tasks.getTasks());
    }


}
