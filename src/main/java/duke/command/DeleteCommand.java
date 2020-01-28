package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * Removes the task from the list of tasks and print relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @throws DukeException If index of out of range of the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.removeTask(index);
        ui.showMessages(new String[] {"Noted. I've removed this task:", " " + task.toString(),
                "Now you have " + tasks.getSize() + " tasks in the list."});
    }
}
