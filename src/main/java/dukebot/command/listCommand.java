package dukebot.command;

import dukebot.LineName;
import dukebot.Storage;
import dukebot.Ui;
import dukebot.tasklist.TaskList;

public class listCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayLine(LineName.LIST);
        if (taskList.size() == 0) {
            ui.sayLine(LineName.LIST_EMPTY);
        } else {
            ui.sayTasks(taskList.taskList);
        }
    }
}
