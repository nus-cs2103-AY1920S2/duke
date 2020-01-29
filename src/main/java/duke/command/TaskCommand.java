package duke.command;

import duke.main.TaskList;
import duke.task.Task;

public class TaskCommand extends Command {
    public static boolean addTask(TaskList taskList, Task t) {
        return taskList.addTask(t) >= 0;
    }
}
