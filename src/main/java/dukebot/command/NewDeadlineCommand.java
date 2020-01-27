package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.Deadline;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class NewDeadlineCommand extends Command {
    private String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public NewDeadlineCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String description;
        String time;
        Task event;
        int byInd = Arrays.asList(inpArr).indexOf("/by");
        if (byInd == inpArr.length - 1) {
            ui.sayLine(LineName.DEADLINE_EMPTY);
            return;
        } else if (byInd > 1) {
            description = String.join(" ", Arrays.copyOfRange(inpArr, 1, byInd));
            time = String.join(" ", Arrays.copyOfRange(inpArr, byInd + 1, inpArr.length));
            try {
                LocalDateTime parsedDate = DateTimeParse.parseDate(time);
                event = new Deadline(description, parsedDate);
            } catch (DateTimeParseException e) {
                ui.sayLine(LineName.DATE_TIME_PARSE_FAIL);
                return;
            }
        } else {
            ui.sayLine(LineName.DEADLINE_EMPTY);
            return;
        }

        try {
            taskList.addTask(event);
            storage.saveToFile(taskList);
            ui.newTask(event);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}