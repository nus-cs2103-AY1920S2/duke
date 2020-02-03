package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            int storageSize = storage.size();
            Ui.printDel(storage.get(index).toString(), storageSize - 1);
            storage.remove(index);
            storageController.writeTask(storage);
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}
