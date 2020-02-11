import java.io.IOException;

/**
 * DoneCommand class handles user's request to mark a task in the task list as completed.
 */
public class DoneCommand extends Command {
    int index;

    /**
     * Creates a new DoneCommand.
     * @param index index position of task that user wants to mark as completed.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the done command. Marks specified task from the task list as completed.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the delete command.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        checkDoneException(tasks);
        return tasks.done(this.index, storage);
    }

    /**
     * Checks command for valid index number.
     * @param tasks list of tasks.
     * @throws DukeException if user input does not follow input format.
     */
    public void checkDoneException(TaskList tasks) throws DukeException {
        // list has 5 items, tasks.size() = 5, this.index max = 4
        if (tasks.size() <= this.index) {
            throw new DukeException("☹ OOPS!!! Task " + (this.index + 1) + " does not exist.\n");
        }
    }

}