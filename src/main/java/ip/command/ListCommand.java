package ip.command;

import ip.Ui;
import ip.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayAllTasks(tasks);
    }
}
