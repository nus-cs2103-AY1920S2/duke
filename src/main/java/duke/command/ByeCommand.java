package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {
    public ByeCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printGoodbye();
    }
}