/**
 * NullCommand takes care of all commands and errors
 */

package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

public class NullCommand extends Command {

    public NullCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("");
    }
}