package seedu.duke.command;

import seedu.duke.InvalidCommandException;
import seedu.duke.InvalidDateException;
import seedu.duke.InvalidTaskInputException;
import seedu.duke.EmptyDescriptionException;
import seedu.duke.TaskIndexOutOfBoundsException;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.Storage;

import java.io.IOException;

public abstract class Command {
    public Command() {

    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws EmptyDescriptionException,
            InvalidCommandException, InvalidTaskInputException, InvalidDateException, IOException, TaskIndexOutOfBoundsException;

    public abstract boolean hasNextCommand();

}
