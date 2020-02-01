package duke.command;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.MissingDeadlineParamException;
import duke.exceptions.MissingDescriptionException;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends TaskCommand {
    public static boolean run(TaskList taskList, String param) throws MissingDescriptionException,
        MissingDeadlineParamException, InvalidArgumentException {
        if (param.equals("")) {
            throw new MissingDescriptionException();
        } else if (!param.contains(" /by ")) {
            throw new MissingDeadlineParamException();
        }
        String[] params = param.split(" /by ");

        Task t = new Deadline(params[0], params[1]);
        if (addTask(taskList, t)) {
            Ui.taskAdded(t, taskList);
        }
        return true;
    }
}
