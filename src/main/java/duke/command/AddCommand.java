package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the Command for the "add" input by the user.
 * It adds a new task to the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class AddCommand extends Command {

    private String toAdd;
    private Task.TaskType taskType;

    /**
     * AddCommand constructor.
     * @param toAdd string name of new task.
     * @param taskType of the new task.
     */
    public AddCommand(String toAdd, Task.TaskType taskType) {
        this.toAdd = toAdd;
        this.taskType = taskType;
    }

    /**
     * Executes Add behaviour of adding a new task.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        Task newTask = taskList.addTask(toAdd, taskType);
        if (newTask != null) {
            storage.saveTaskToFile(newTask);
        }
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
