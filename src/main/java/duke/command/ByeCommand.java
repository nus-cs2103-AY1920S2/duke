package duke.command;

import duke.task.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        ui.printFarewellMessage();
        return false;
    }
}