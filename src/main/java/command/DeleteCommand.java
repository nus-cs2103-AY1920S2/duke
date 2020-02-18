package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A command object for deleting tasks from the list.
 */
public class DeleteCommand extends Command {
    private final Integer taskNumber;

    /**
     * Constructs a command object to delete task from the list.
     * @param taskNumber The index of the task to be deleted.
     */
    public DeleteCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete task from the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.taskNumber > tasks.size()) {
                return Ui.showException(new DukeException(
                        "☹ OOPS!!! You only have " + tasks.size() + " tasks in the list, you cannot delete task number " + taskNumber));
            }
            Task removedTask = tasks.get(taskNumber - 1);
            tasks.remove(removedTask);
            storage.deleteFromFile(taskNumber);
            return Ui.showRemove(removedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return Ui.showException(new DukeException(
                    "☹ OOPS!!! The description of a delete cannot be empty."));
        } catch (FileNotFoundException e) {
            return Ui.showException(new DukeException(
                    "☹ OOPS!!! The file of duke.txt can't be found, list not updated."));
        } catch (IOException e) {
            return Ui.showException(new DukeException(
                    "☹ OOPS!!! The file of duke.txt can't be updated, list not updated."));
        }
    }
}
