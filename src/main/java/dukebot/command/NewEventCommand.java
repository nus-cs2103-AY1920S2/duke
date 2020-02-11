package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Event;
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
 * Command to create a new event.
 */
public class NewEventCommand extends Command {
    private final String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public NewEventCommand(String[] inpArr) {
        assert inpArr != null;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        TaskList taskList = appStorage.getTaskList();
        int atInd = Arrays.asList(inpArr).indexOf("/at");
        if (atInd == inpArr.length - 1) {
            ui.sayLine(LineName.EVENT_EMPTY);
            return;
        } else if (atInd <= 1) {
            ui.sayLine(LineName.EVENT_AT_MISSING);
            return;
        }
        String description = String.join(" ", Arrays.copyOfRange(inpArr, 1, atInd));
        String time = String.join(" ", Arrays.copyOfRange(inpArr, atInd + 1, inpArr.length));
        LocalDateTime parsedDate = null;
        try {
            parsedDate = DateTimeParse.parseDate(time);
        } catch (DateTimeParseException e) {
            ui.sayLine(LineName.DATE_TIME_PARSE_FAIL);
            return;
        }
        Task event = new Event(description, parsedDate);
        taskList.addTask(event);
        ui.sayLineWithTask(LineNameWithTask.NEW_TASK_SUCCESS, event);

        try {
            storage.saveTaskList(taskList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}