package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a done command.
 * The command deals with marking tasks as done.
 */
public class DoneCommand extends Command {

    protected int index;

    /**
     * Constructs a DoneCommand with specified index of a task in the list of tasks.
     *
     * @param index The specified index of the task in the list of tasks.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done and print relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @throws DukeException If index is out of range of the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.showMessages(new String[]{"Nice! I've marked this task as done:",
                " " + task.toString()});
    }
}
