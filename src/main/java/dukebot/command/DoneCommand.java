package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.LineNameWithTask;
import dukebot.ui.Ui;
import dukebot.util.MiscUtils;

/**
 * Command to set task as done.
 */
public class DoneCommand extends Command {
    private final String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr The input entered by user split by space
     */
    public DoneCommand(String[] inpArr) {
        assert inpArr != null;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        TaskList taskList = appStorage.getTaskList();
        if (inpArr.length == 1) {
            ui.sayLine(LineName.DONE_EMPTY);
            return;
        }

        if (!MiscUtils.isInteger(inpArr[1])) {
            ui.sayLine(LineName.NOT_A_NUMBER);
            return;
        }

        int taskInd = Integer.parseInt(inpArr[1]) - 1;
        Task doneTask = taskList.getTask(taskInd);

        if (doneTask == null) {
            ui.sayLine(LineName.DEFAULT_OUT_OF_INDEX);
            return;
        }

        if (doneTask.getDone()) {
            ui.sayLine(LineName.DONE_ALREADY);
            return;
        }

        ui.sayLineWithTask(LineNameWithTask.DONE_SUCCESS, doneTask);
        doneTask.setDone();

        try {
            storage.saveTaskList(taskList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }

}
