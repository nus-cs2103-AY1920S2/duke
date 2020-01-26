package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

public class AddCommand extends Command {

    private String toAdd;
    private Task.TaskType taskType;

    public AddCommand(String toAdd, Task.TaskType taskType) {
        this.toAdd = toAdd;
        this.taskType = taskType;
    }

    public void execute(TaskList taskList, Storage storage) {
        Task newTask = taskList.AddTask(toAdd, taskType);
        if (newTask != null)
            storage.SaveTaskToFile(newTask);
    }

    public boolean isExit() {
        return false;
    }
}
