package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Deadline;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.LineNameWithTask;
import dukebot.ui.Ui;
import dukebot.util.DateTimeParse;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Command to create a deadline.
 */
public class NewDeadlineCommand extends Command {
    private final String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public NewDeadlineCommand(String[] inpArr) {
        assert inpArr != null;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        TaskList taskList = appStorage.getTaskList();
        String description;
        String time;
        Task deadline;
        int byInd = Arrays.asList(inpArr).indexOf("/by");
        if (byInd == inpArr.length - 1) {
            ui.sayLine(LineName.DEADLINE_EMPTY);
            return;
        } else if (byInd > 1) {
            description = String.join(" ", Arrays.copyOfRange(inpArr, 1, byInd));
            time = String.join(" ", Arrays.copyOfRange(inpArr, byInd + 1, inpArr.length));
            try {
                LocalDateTime parsedDate = DateTimeParse.parseDate(time);
                deadline = new Deadline(description, parsedDate);
            } catch (DateTimeParseException e) {
                ui.sayLine(LineName.DATE_TIME_PARSE_FAIL);
                return;
            }
        } else {
            ui.sayLine(LineName.DEADLINE_EMPTY);
            return;
        }

        try {
            taskList.addTask(deadline);
            storage.saveTaskList(taskList);
            ui.sayLineWithTask(LineNameWithTask.NEW_TASK_SUCCESS, deadline);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}