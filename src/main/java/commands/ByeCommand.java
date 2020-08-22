package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.overwrite(taskList.getTasks());
        return ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
