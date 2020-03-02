package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.TaskFormatException;
import duke.exception.InvalidTimeFormatException;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, EmptyDescriptionException, TaskFormatException, InvalidTimeFormatException {
        return tasks.manageDeadline(storage, input);
    }
}