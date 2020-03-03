package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getListResponse(taskList);
    }
}
