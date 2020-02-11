/**
 * ExitCommand exits the program
 */

package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Bye. Hope to see you again soon!");
    }
}