package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTimeFormatException;
import duke.exception.TaskFormatException;

public class EventCommand extends Command {
    public EventCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, TaskFormatException, InvalidTimeFormatException {
        return tasks.manageEvent(storage, input);
    }
}