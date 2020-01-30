package duke.command;

import duke.task.*;
import duke.other.*;

/**
 * Represents a Command(i.e. AddCommand, DeleteCommand or ShowCommand). AddCommand, DeleteCommand or ShowCommand extend
 * this class.
 */
public abstract class Command {
    public boolean isExit;
    public Command() {
        isExit = false;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
