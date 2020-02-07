package duke.command;

import duke.task.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.printFarewellMessage();
    }
}