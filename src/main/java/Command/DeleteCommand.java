package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand implements Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return "";
    }
}
