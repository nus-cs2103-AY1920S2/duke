package duke;

import duke.command.Command;

/**
 * The Controller class that facilitates execution of the Duke program
 */
public class Controller {
    private Storage storageController;

    /**
     * Constructs a Controller object with the specified Storage object
     *
     * @param storageController the Storage object
     */
    public Controller(Storage storageController) {
        this.storageController = storageController;
    }

    /**
     * Executes the parametric Command object.
     *
     * @param command a Command object.
     * @return a boolean value that is returned by the method call execute() to the Command object.
     */
    public boolean execute(Command command) {
        return command.execute(storageController, storageController.generateTaskList());
    }
}
