package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A class representing an addition command.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand object with a specified Task object.
     *
     * @param task a Task object to be added by Duke.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this AddCommand and writes the specified task represented by the Task object to the file that is
     * currently specified by the file path in the Duke instance.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return false
     */
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
