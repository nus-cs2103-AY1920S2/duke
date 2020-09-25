package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TasksList;

/**
 * The base class for all command which allow each command to be its own separate
 * script that encapsulated all the information they need.
 */
public abstract class Command {
    public boolean isExit;

    /**
     * Constructs a command, by default the command is not an exit (the program) command
     * so isExit is set to false
     */
    public Command(){
        isExit = false;
    }

    /**
     * makes all command need to be able the execute, the parameters provide subclasses with
     * objects that they all generally need.
     *
     * @param tasksList the tasksList containing all tasks Duke current has
     * @param ui used to manage input/output to the Command Line Interface
     * @param storage for saving the updated tasks list to the save file
     *
     * @exception DukeException used to signal errors in execution.
     * */
    public abstract void execute(TasksList tasksList, Ui ui, Storage storage) throws DukeException;
}
