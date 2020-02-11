/**
 * Abstract class command for all types of command
 */

package duke.commands;

import java.io.IOException;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

public abstract class Command {

    String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException ;
}
