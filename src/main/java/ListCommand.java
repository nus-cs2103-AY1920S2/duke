package duke.commands;

import duke.commands.Command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * ListCommand class handles user's request to list and print all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command. Prints all tasks in the task list.
     *
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage makeshift database for tasks
     * @throws DukeException if user input does not follow input format
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be open for any other reason.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.printList();
    }

    /**
     * Returns a boolean that determines if command exits the program.
     *
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }

}