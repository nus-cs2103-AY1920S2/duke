package duke.command;

import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        ui.printList(taskList);
        return true;
    }
}