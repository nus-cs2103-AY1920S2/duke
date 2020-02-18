package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.manageDeadline(storage, input, storage.getFileName());
    }
}