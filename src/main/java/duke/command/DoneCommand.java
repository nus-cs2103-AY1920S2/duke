package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A class representing a completion command.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructs the DeleteCommand object with specified index for completion i.
     *
     * @param i a nonnegative, integer index for a Task object in the currently processed ArrayList.
     */
    public DoneCommand(int i) {
        this.index = i;
    }

    /**
     * Executes this DoneCommand.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return false
     */
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
