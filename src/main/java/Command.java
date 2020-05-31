import java.io.IOException;

/**
 * Abstract Command class handles user's request.
 */
public abstract class Command {

    /**
     * Abstract execute method that handles user input.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}