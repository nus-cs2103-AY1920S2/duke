package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(task);
        ui.displayAddedTask(task, taskList);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}