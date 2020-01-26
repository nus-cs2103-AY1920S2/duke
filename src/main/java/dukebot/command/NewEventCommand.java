package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.Event;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class NewEventCommand extends Command {
    private String[] inpArr;

    public NewEventCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String description;
        String time;
        Task event = null;
        int atInd = Arrays.asList(inpArr).indexOf("/at");
        if (atInd == inpArr.length - 1) {
            ui.sayLine(LineName.EVENT_EMPTY);
            return;
        } else if (atInd > 1) {
            description = String.join(" ", Arrays.copyOfRange(inpArr, 1, atInd));
            time = String.join(" ", Arrays.copyOfRange(inpArr, atInd + 1, inpArr.length));
            try {
                LocalDateTime parsedDate = DateTimeParse.parseDate(time);
                event = new Event(description, parsedDate);
            } catch (DateTimeParseException e) {
                ui.sayLine(LineName.DATE_TIME_PARSE_FAIL);
                return;
            }
        } else {
            ui.sayLine(LineName.EVENT_AT_MISSING);
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