package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.LineNameWithTask;
import dukebot.ui.Ui;
import dukebot.util.DateTimeParse;
import dukebot.util.MiscUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Command to reschedule a task.
 */
public class RescheduleCommand extends Command {
    private String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr The input entered by user split by space
     */
    public RescheduleCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        TaskList taskList = appStorage.getTaskList();
        if (inpArr.length <= 2) {
            ui.sayLine(LineName.RESCHEDULE_EMPTY);
            return;
        }
        if (!MiscUtils.isInteger(inpArr[1])) {
            ui.sayLine(LineName.NOT_A_NUMBER);
            return;
        }

        int taskInd = Integer.parseInt(inpArr[1]) - 1;
        Task task = taskList.getTask(taskInd);
        if (task == null) {
            ui.sayLine(LineName.DEFAULT_OUT_OF_INDEX);
            return;
        }
        if (task.getDateTime() == null) {
            ui.sayLineWithTask(LineNameWithTask.RESCHEDULE_BAD_TASK, task);
            return;
        }

        String time = String.join(" ", Arrays.copyOfRange(inpArr, 2, inpArr.length));
        try {
            LocalDateTime parsedDate = DateTimeParse.parseDate(time);
            task.setDateTime(parsedDate);
            ui.sayLineWithTask(LineNameWithTask.RESCHEDULE_SUCCESS, task);
            storage.saveTaskList(taskList);
        } catch (DateTimeParseException e) {
            ui.sayLine(LineName.DATE_TIME_PARSE_FAIL);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}