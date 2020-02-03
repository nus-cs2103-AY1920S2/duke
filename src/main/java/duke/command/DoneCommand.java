package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            int storageSize = storage.size();
            storage.get(index).setDone();
            Ui.printDone(storage.get(index).toString(), storageSize);
            storageController.writeTask(storage);
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}
