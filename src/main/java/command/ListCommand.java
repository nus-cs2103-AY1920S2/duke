package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}