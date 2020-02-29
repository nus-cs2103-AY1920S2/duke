package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.Storage;

public class TodoCommand extends Command {
    public TodoCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        return tasks.manageTodo(storage, input, storage.getFileName());
    }
}