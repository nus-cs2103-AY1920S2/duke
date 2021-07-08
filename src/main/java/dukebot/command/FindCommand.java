package dukebot.command;

import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Command to find a substring in a task's description.
 */
public class FindCommand extends Command {
    private final String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public FindCommand(String[] inpArr) {
        assert inpArr != null;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        TaskList taskList = appStorage.getTaskList();
        String toFind = String.join(" ", Arrays.copyOfRange(inpArr, 1, inpArr.length));
        if (toFind.length() == 0) {
            ui.sayLine(LineName.FIND_EMPTY);
            return;
        }
        ArrayList<Task> tasksFound = taskList.findAll(toFind);
        if (tasksFound.size() == 0) {
            ui.sayLine(LineName.FIND_FAIL);
            return;
        }
        ui.sayLine(LineName.FIND_SUCCESS);
        ui.sayTasks(tasksFound);
    }
}
