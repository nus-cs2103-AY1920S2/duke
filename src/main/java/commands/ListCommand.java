package commands;

import exception.EmptyTaskListException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskListException {
        return ui.acknowledgeList(tasks);
    }
}
