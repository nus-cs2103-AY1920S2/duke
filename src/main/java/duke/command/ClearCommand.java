package duke.command;

import duke.main.TaskList;
import duke.main.Ui;

public class ClearCommand extends Command {
    public static boolean run(TaskList taskList) {
        taskList.clearTasks();
        Ui.tasksCleared();
        return true;
    }
}
