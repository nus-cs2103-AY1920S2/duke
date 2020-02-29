package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTimeFormatException;


import duke.Storage;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, InvalidTimeFormatException {
        return tasks.manageDeadline(storage, input);
    }
}