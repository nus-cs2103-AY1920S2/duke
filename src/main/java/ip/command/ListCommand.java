package ip.command;

import ip.Ui;
import ip.task.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.displayAllTasks(tasks);
    }
}
