package duke.command;

import duke.Controller;
import duke.Storage;
import duke.Ui;
import duke.state.StateController;
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

    public int getIndex() {
        return this.index;
    }

    /**
     * Executes this DoneCommand.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return false
     */
    @Override
    public boolean execute(StateController stateController, Storage storageController, ArrayList<Task> storage) {
        try {
            int storageSize = storage.size();
            if (index > storage.size()) {
                Controller.raiseException(new Exception("This is beyond the scope of my ability, sadly."));
                return false;
            }
            storage.get(index).setDone();
            Ui.printDone(storage.get(index).toString(), storageSize - Controller.getNumberOfDoneTasks(storage));
            storageController.writeTask(storage);
            stateController.commit(this, storage);
        } catch (Exception e) {
            Controller.raiseException(e);
        }
        return false;
    }
}
