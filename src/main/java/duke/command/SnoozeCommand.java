package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.SnoozeInvalidEventTypeException;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SnoozeCommand extends Command {
    public static boolean run(TaskList taskList, String param) throws DukeException {
        try {
            String[] params = param.split(" ", 2);
            int index = Integer.parseInt(params[0]);
            Task task = taskList.getTask(index - 1);
            DateTimeFormatter inputDtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime outputDt = LocalDateTime.parse(params[1], inputDtf);
            DateTimeFormatter outputDtf = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");
            LocalDateTime datetime = LocalDateTime.parse(outputDt.format(outputDtf), DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
            switch (task.getClass().getName()) {
            case "duke.task.Event":
                ((Event) task).snooze(datetime);
                Ui.taskSnoozed(task);
                break;
            case "duke.task.Deadline":
                ((Deadline) task).snooze(datetime);
                Ui.taskSnoozed(task);
                break;
            default:
                throw new SnoozeInvalidEventTypeException();
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new InvalidArgumentException();
        } catch (DukeException exception) {
            throw exception;
        }
        return true;
    }
}
