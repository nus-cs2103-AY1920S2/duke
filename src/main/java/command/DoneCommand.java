package command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

import task.Task;

/**
 * A command object for changing task's status to done.
 */
public class DoneCommand extends Command {
    private final Integer taskNumber;

    /**
     * Constructs a command object to change a task to done state.
     * @param taskNumber The index of the task to be changed.
     */
    public DoneCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to change task's status in the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task updatedTask = tasks.get(this.taskNumber - 1).setDone();
            tasks.set(this.taskNumber - 1, updatedTask);
            storage.updateDrive(updatedTask);
            return ui.showDone(updatedTask);
        } catch (IndexOutOfBoundsException e) {
            return ui.showException(new DukeException(
                    "☹ OOPS!!! The description of a done cannot be empty."));
        }
    }
}
