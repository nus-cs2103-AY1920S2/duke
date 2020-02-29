package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}