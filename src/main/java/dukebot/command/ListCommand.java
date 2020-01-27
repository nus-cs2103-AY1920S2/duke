package dukebot.command;

import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            ui.sayLine(LineName.LIST_EMPTY);
        } else {
            ui.sayLine(LineName.LIST);
            ui.sayTasks(taskList.getTaskList());
        }
    }
}
