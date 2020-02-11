package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteAllCommand extends Command{
    public DeleteAllCommand() {

    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.deleteAll();
        ui.displayTasks(taskList);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
