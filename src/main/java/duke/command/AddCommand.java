package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;


public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            storage.add(task);
            int storageSize = storage.size();
            Ui.printAdd(task.toString(), storageSize);
            storageController.writeTask(storage);
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}
