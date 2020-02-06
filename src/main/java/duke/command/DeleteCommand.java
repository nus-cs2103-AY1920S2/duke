package duke.command;

import duke.Controller;
import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A class representing a deletion command.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs the DeleteCommand object with specified index for deletion i.
     *
     * @param i a nonnegative, integer index for a Task object in the currently processed ArrayList.
     */
    public DeleteCommand(int i) {
        this.index = i;
    }

    /**
     * Executes this DeleteCommand. The Task object is deleted from the file specified by the file path in the current
     * Duke instance.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return false
     */
    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            int storageSize = storage.size();
            Ui.printDel(storage.get(index).toString(), storageSize - 1);
            storage.remove(index);
            storageController.writeTask(storage);
        } catch (Exception e) {
            Controller.raiseException(e);
        }
        return false;
    }

    @Override
    public void executeGui(Storage storageController, ArrayList<Task> storage) {
        execute(storageController, storage);
    }
}
