package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

import duke.exception.TaskIndexException;

public class DeleteCommand extends Command {
    public DeleteCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException {
        String[] split = this.input.split(" ");
        int idx = Integer.parseInt(split[1]);
        storage.deleteTask(idx);
        return tasks.deleteTask(idx - 1);
    }
}