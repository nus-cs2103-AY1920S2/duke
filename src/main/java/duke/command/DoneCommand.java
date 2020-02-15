package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Marks a task in the TaskList as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructs a DoneCommand with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the TaskList as done and returns an acknowledgement message.
     *
     * @param tasks The TaskList where the task is to be marked as done.
     * @return A string with the message to be printed.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.get(index);
        task.markAsDone();
        assert task.getStatusIcon().equals("Y") : "Task should be marked as done";
        return Ui.showToUser(Ui.MESSAGE_DONE, Ui.INDENT + task);
    }
}
