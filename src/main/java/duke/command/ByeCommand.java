package duke.command;

import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
