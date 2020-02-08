package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

/**
 * Represents a delete command.
 * The command deals with removing tasks from the list of tasks.
 */
public class DeleteCommand extends Command {

    protected int index;

    /**
     * Constructs a DeleteCommand with specified index of a task in the list of tasks.
     *
     * @param index The specified index of the task in the list of tasks.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes the task from the list of tasks and returns relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @return The relevant messages in the form of String.
     * @throws DukeException If index of out of range of the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert (index < tasks.getSize() && index >= 0) : "Index out of range!";
        Task task = tasks.removeTask(index);
        String[] details = new String[] {" " + task.toString(), tasks.getSize() + ""};
        return ui.generateDeleteMessage(details);
    }
}
