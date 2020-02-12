package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Deletes a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the specified index in the TaskList and returns an acknowledgement message.
     *
     * @param tasks The TaskList where the task is to be deleted.
     * @return A string with the message to be printed.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.remove(index);
        return Ui.showToUser(Ui.MESSAGE_DELETE, Ui.INDENT + task, Ui.getNumberOfTasksMessage(tasks));
    }
}
