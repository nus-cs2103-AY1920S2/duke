import java.io.IOException;

/**
 * DoneCommand class handles user's request to mark a task in the task list as completed.
 */
public class UpdateCommand extends Command {
    int index;
    String newDescription;

    /**
     * Creates a new UpdateCommand.
     * @param description new description of specified tasks.
     */
    public UpdateCommand(String description) {
        String[] split = description.split(" ");

        this.index = Integer.parseInt(split[0]) - 1;
        this.newDescription = split[1];
    }

    /**
     * Executes the update command. Updates a specific task with a new description.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the delete command.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        checkUpdateException(tasks);
        return tasks.update(this.index, newDescription, storage);
    }

    /**
     * Checks command for valid index number.
     * @param tasks list of tasks.
     * @throws DukeException if user input does not follow input format.
     */
    public void checkUpdateException(TaskList tasks) throws DukeException {
        // list has 5 items, tasks.size() = 5, this.index max = 4
        if (tasks.size() <= this.index) {
            throw new DukeException("☹ OOPS!!! Task " + (this.index + 1) + " does not exist.\n");
        }
    }

}