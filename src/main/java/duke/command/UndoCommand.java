package duke.command;

import duke.Controller;
import duke.Storage;
import duke.Ui;
import duke.state.State;
import duke.state.StateController;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A class representing the undo command.
 */
public class UndoCommand extends Command {
    /**
     * Executes an undo command
     *
     * @param stateController   a StateController object that stores states and facilitates the operation.
     * @param storageController a Storage object
     * @param storage           an ArrayList collection of Task objects for processing in-program.
     * @return false
     */
    @Override
    public boolean execute(StateController stateController, Storage storageController, ArrayList<Task> storage) {
        try {
            stateController.undo();
            State state = stateController.getCurrent();
            System.out.println(state.getTaskList().get(state.getTaskList().size() - 1));
            storageController.writeTask(state.getTaskList());
            Ui.printUndo();
        } catch (Exception e) {
            Controller.raiseException(e);
        }
        return false;
    }
}
