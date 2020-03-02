package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.Message;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.TaskIndexException;

public class DeleteCommand extends Command {
    public DeleteCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException, InvalidCommandException {
        String[] split = this.input.split(" ");
        if (split.length < 2) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        } else if (split.length > 2) {
            throw new InvalidCommandException(Message.DELETE_ERROR);
        }

        try {
            int idx = Integer.parseInt(split[1]);
            storage.deleteTask(idx);
            return tasks.deleteTask(idx - 1);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Message.DELETE_ERROR);
        }
    }
}