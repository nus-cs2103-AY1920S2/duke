import java.io.IOException;

/**
 * DeleteCommand class handles user's request to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    int index;

    /**
     * Creates a new DeleteCommand.
     * @param index index position of task that user wants to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command. Deletes specified task from the task list.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the delete command.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return tasks.delete(this.index, storage);
    }

    /**
     * Checks command for valid index number.
     * @param tasks list of tasks.
     * @throws DukeException if user input does not follow input format.
     */
    public void checkDeleteException(TaskList tasks) throws DukeException {
        // list has 5 items, tasks.size() = 5, this.index max = 4
        if (tasks.size() <= this.index) {
            throw new DukeException("☹ OOPS!!! Task " + (this.index + 1) + " does not exist.\n");
        }
    }

}