package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Represents the Command used to terminate application.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.showFarewellMessage();
    }
}