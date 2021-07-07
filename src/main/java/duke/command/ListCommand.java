package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.gui.Gui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showList(taskList.getTaskList(), "Here are the tasks in your list:");
    }

    @Override
    public String execute(TaskList taskList, Gui gui) {
        return gui.showList(taskList.getTaskList(), "Here are the tasks in your list:");
    }
}
