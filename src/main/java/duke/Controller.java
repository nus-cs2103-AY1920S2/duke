package duke;

import duke.command.Command;
import duke.state.StateController;
import duke.task.Task;

import java.util.ArrayList;

/**
 * The Controller class that facilitates execution of the Duke program.
 */
public class Controller {
    protected Storage storageController;
    private StateController stateController;
    private boolean hasReachedExit = false;

    public static int getNumberOfDoneTasks(ArrayList<Task> storage) {
        return (int) storage.stream().filter(task -> task.getStatus().equals("T")).count();
    }

    /**
     * Constructs a Controller object with the specified Storage object.
     *
     * @param storageController the Storage object
     */
    public Controller(Storage storageController) {
        this.storageController = storageController;
        this.stateController = new StateController(storageController.generateTaskList());
    }

    public static void raiseException(Exception e) {
        Ui.printError(e);
    }

    /**
     * Executes the parametric Command object.
     *
     * @param command a Command object.
     * @return a boolean value that is returned by the method call execute() to the Command object.
     */
    public boolean execute(Command command) {
        hasReachedExit = command.execute(stateController, storageController, storageController.generateTaskList());
        return hasReachedExit;
    }

    /**
     * Gets the status of the current run of Duke.
     *
     * @return true iff the latest successful command is an exit command, false otherwise.
     */
    public boolean getStatus() {
        return hasReachedExit;
    }
}
