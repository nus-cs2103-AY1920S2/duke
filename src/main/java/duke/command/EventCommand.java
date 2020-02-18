package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class EventCommand extends Command {
    public EventCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.manageEvent(storage, input, storage.getFileName());
    }
}