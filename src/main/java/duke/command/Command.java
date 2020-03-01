package duke.command;

import duke.Storage;
import duke.state.StateController;
import duke.task.Task;

import java.util.ArrayList;

/**
 * An abstract object representing a command to the Duke program.
 */
public abstract class Command {
    /**
     * Executes a command using the specified Storage object and an ArrayList collection of Task objects.
     *
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return true if the Command is an ExitCommand, false otherwise.
     */
    public abstract boolean execute(StateController stateController, Storage storageController,
                                    ArrayList<Task> storage);
}

