package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
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
     * @param tasks The TaskList where the task is to be deleted.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @return A string with the message to be printed.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.remove(index);
        return ui.showToUser("Noted. I've removed this task: ", "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
