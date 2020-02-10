package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Represents the Command used to get List of Tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.showList(taskList);
    }
}