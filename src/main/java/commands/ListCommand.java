package commands;

import exception.EmptyTaskListException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    /**
     * Lists all tasks.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return list all tasks response.
     * @throws EmptyTaskListException if the list is empty when starting up.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskListException {
        return ui.acknowledgeList(tasks);
    }
}
