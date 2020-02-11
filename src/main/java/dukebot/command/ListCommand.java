package dukebot.command;

import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.LineNameWithTask;
import dukebot.ui.Ui;

import java.util.ArrayList;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        TaskList taskList = appStorage.getTaskList();
        if (taskList.size() == 0) {
            ui.sayLine(LineName.LIST_EMPTY);
            return;
        }
        ui.sayLine(LineName.LIST_EXISTS);
        ui.sayTasks(taskList.getTaskList());
    }
}
