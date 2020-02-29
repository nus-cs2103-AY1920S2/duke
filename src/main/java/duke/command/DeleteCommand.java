package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.exception.TaskIndexException;
import duke.Storage;

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