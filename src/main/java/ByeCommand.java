package duke.commands;

import duke.commands.Command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * ByeCommand class handles user's request to exit program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the exit command and ends the program.
     *
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be open for any other reason.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Returns a boolean that determines if command exits the program.
     *
     * @return boolean.
     */
    public boolean isExit() {
        return true;
    }

}