package seedu.duke.command;

import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;

import java.io.IOException;

/**
 * Represents a general Command object.
 */
public abstract class Command {
    /**
     * Represents a Command object.
     */
    public Command() {

    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    public abstract boolean hasNextCommand();

}
