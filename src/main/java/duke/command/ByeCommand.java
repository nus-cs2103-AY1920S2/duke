package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printGoodbye();
    }
}