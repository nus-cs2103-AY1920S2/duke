package duke.command;

import duke.main.TaskList;
import duke.main.Ui;

public class ClearCommand extends Command {
    public static boolean run(TaskList taskList) {
        taskList.clearTasks();
        // Ensure that the TaskList has been emptied
        assert(taskList.size() == 0);
        Ui.tasksCleared();
        return true;
    }
}
