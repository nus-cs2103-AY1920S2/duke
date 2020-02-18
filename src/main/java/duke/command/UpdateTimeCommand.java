package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class UpdateTimeCommand extends Command {
    public UpdateTimeCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] split = this.input.split(" ");
        int idx = Integer.parseInt(split[2]);
        storage.updateTime(idx, input);
        return tasks.updateTime(idx - 1, input);
    }
}