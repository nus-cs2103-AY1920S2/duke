package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findKeyword(input);
    }
}