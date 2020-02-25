package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents as an outline what a command carried about by
 * Chatbot should be able to do.
 * Any class that extends this class must implement method
 * execute and isExit.
 * Only inherits from this class according to what Chatbot can
 * understand as a command.
 *
 * @author Kenny Ho
 */

public abstract class Command {

    private Task task;

    public Command(Task task) {
        this.task = task;
    }

    public Command() {
        this.task = null;
    }

    /**
     * Getter method to retrieve duke.task.Task object if command
     * requires a duke.task.Task object to be created.
     *
     * @return duke.task.Task object.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Provide the ability any command to:
     * 1) call respective duke.task.TaskList method if command requires
     * any form of functionality provided by duke.task.TaskList class.
     * 2) call respective duke.Ui method if command requires any
     * form of interaction with user.
     * 3) call respective duke.Storage method if command requires
     * any form of functionality provided by duke.Storage class.
     *
     * @param tasks   duke.task.TaskList object providing command the ability to call duke.task.TaskList methods.
     * @param ui      duke.Ui object providing command the ability to call duke.Ui methods.
     * @param storage duke.Storage object providing command the ability to call duke.Storage methods.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Should set boolean to true if command is an exit command otherwise
     * set boolean to false.
     *
     * @return Boolean value representing command is an exit command.
     */
    public boolean isExit() {
        return false;
    };
}

