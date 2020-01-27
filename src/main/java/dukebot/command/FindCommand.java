package dukebot.command;

import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class FindCommand extends Command {
    private String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public FindCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String toFind = String.join(" ", Arrays.copyOfRange(inpArr, 1, inpArr.length));
        if (toFind.length() == 0) {
            ui.sayLine(LineName.FIND_EMPTY);
            return;
        }
        ArrayList<Task> tasksFound = taskList.findAll(toFind);
        if (tasksFound.size() == 0) {
            ui.sayLine(LineName.FIND_FAIL);
        } else {
            ui.sayLine(LineName.FIND_SUCCESS);
            ui.sayTasks(tasksFound);
        }
    }
}
