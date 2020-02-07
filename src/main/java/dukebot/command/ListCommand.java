package dukebot.command;

import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        TaskList taskList = appStorage.getTaskList();
        if (taskList.size() == 0) {
            ui.sayLine(LineName.LIST_EMPTY);
        } else {
            ui.sayLine(LineName.LIST_EXISTS);
            ui.sayTasks(taskList.getTaskList());
        }
    }
}
