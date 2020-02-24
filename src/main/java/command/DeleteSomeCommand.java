package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteSomeCommand extends Command {
    private ArrayList<Integer> idOfTaskListToBeDeleted;

    public DeleteSomeCommand(ArrayList idOfTaskListToBeDeleted) {
        this.idOfTaskListToBeDeleted = idOfTaskListToBeDeleted;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.deleteSome(idOfTaskListToBeDeleted);
        ui.displayDeleteSomeTask(taskList, idOfTaskListToBeDeleted);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
