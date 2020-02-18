/**
 * ExitCommand exits the program
 */

package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return true;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Executors.newSingleThreadScheduledExecutor()
                .schedule(() -> System.exit(0), 1, TimeUnit.SECONDS);
        return "Bye. Hope to see you again soon!";
    }
}