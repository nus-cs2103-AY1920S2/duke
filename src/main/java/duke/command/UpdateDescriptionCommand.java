package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.exception.TaskIndexException;
import duke.Storage;

public class UpdateDescriptionCommand extends Command {
    public UpdateDescriptionCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException {
        String[] split = this.input.split(" ");
        int idx = Integer.parseInt(split[2]);
        storage.updateDescription(idx, input);
        return tasks.updateDescription(idx - 1, input);
    }
}