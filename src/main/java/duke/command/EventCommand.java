package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTimeFormatException;
import duke.Storage;

public class EventCommand extends Command {
    public EventCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException, InvalidTimeFormatException {
        return tasks.manageEvent(storage, input);
    }
}