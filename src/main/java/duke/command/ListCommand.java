package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}