package duke.command;

import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.printList(taskList);
    }
}