package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;

/**
 * Marks a task in the TaskList as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructs a DoneCommand with the specified index.
     * @param index The index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index in the TaskList as done and prints an acknowledgement message.
     * @param tasks The TaskList where the task is to be marked as done.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(index);
        task.markAsDone();
        ui.showDoneTask(task);
    }
}
