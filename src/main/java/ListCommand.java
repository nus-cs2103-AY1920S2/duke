import java.io.IOException;

/**
 * ListCommand class handles user's request to list and print all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command. Prints all tasks in the task list.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the list command.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return tasks.printListString();
    }
}