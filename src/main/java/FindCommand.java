import java.io.IOException;

/**
 * FindCommand class handles user's request to find all tasks contain a search key.
 */
public class FindCommand extends Command {
    String description;

    /**
     * Creates a new FindCommand.
     * @param description keyword that users want to find
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command. Searches all tasks in task list for tasks that contain the keyword.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the find command.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return tasks.find(description);
    }
}