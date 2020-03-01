package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

import duke.exception.TaskIndexException;

public class DoneCommand extends Command {
    public DoneCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException {
        String[] split = this.input.split(" ");
        int idx = Integer.parseInt(split[1]);
        storage.doTask(idx);
        return tasks.doTask(idx - 1);
    }
}