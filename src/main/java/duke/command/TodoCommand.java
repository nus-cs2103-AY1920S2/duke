package duke.command;

import duke.exceptions.MissingDescriptionException;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;
import duke.task.ToDo;

public class TodoCommand extends TaskCommand {
    public static boolean run(TaskList taskList, String param) throws MissingDescriptionException {
        if (param.equals("")) {
            throw new MissingDescriptionException();
        }
        Task t = new ToDo(param);
        if (addTask(taskList, t)) {
            Ui.taskAdded(t, taskList);
        }
        return true;
    }
}
