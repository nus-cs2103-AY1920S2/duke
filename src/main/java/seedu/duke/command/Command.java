package seedu.duke.command;

import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.InvalidDateException;
import seedu.duke.exception.InvalidTaskInputException;
import seedu.duke.exception.TaskIndexOutOfBoundsException;
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

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws EmptyDescriptionException,
            InvalidCommandException, InvalidTaskInputException, InvalidDateException, IOException, TaskIndexOutOfBoundsException, EmptyDescriptionException, InvalidCommandException, InvalidTaskInputException, InvalidDateException;

    public abstract boolean hasNextCommand();

}
