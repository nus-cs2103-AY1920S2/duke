package duke.command;

import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.MissingDeadlineParamException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingEventParamException;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends TaskCommand {
    public static boolean run(TaskList taskList, String param) throws MissingDescriptionException
        , MissingEventParamException, InvalidDateTimeFormatException {
        if (param.equals("")) {
            throw new MissingDescriptionException();
        } else if (!param.contains(" /at ")) {
            throw new MissingEventParamException();
        }
        String[] params = param.split(" /at ");
        Task t = new Event(params[0], params[1]);
        if (addTask(taskList, t)) {
            Ui.taskAdded(t, taskList);
        }
        return true;
    }
}
