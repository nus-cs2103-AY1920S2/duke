package duke.command;

import duke.core.Message;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.TaskIndexException;

public class DoneCommand extends Command {
    public DoneCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException, InvalidCommandException {
        String[] split = this.input.split(" ");
        if (split.length < 2) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        } else if (split.length > 2) {
            throw new InvalidCommandException(Message.DONE_ERROR);
        }
        int idx = Integer.parseInt(split[1]);
        storage.doTask(idx);
        return tasks.doTask(idx - 1);
    }
}