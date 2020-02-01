package entity.command;

import entity.TaskList;
import handler.Storage;
import handler.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listAllTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
