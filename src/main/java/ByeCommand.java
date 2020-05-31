import java.io.IOException;

/**
 * ByeCommand class handles user's request to exit program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the exit command and ends the program.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the bye command.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return "Bye. Hope to see you again soon!";
    }

}