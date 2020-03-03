package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = ui.showByeMessage();
        storage.overwrite(taskList.getTasks());
        System.out.println(response);
        return response;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
