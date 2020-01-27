package command;
import java.io.IOException;

import storage.Storage;

import task.Task;
import task.TaskList;

import ui.Ui;
/**
 * Represents the command of deleting a task using the index of the task. A <code>DeleteCommand</code> object
 * corresponds to the command of deleting a task using the index e.g., <code>2</code>
 */
public class DeleteCommand extends Command {
    int taskNum;
    public DeleteCommand(int taskNum){
        this.taskNum = taskNum;
    }
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */
    public boolean isExit() {
        return false;
    }
    /**
     * Deletes a task.
     * If storage file is invalid, an error message is returned.
     * @param tasks The task list to be updated.
     * @param ui The user interface to be to be shown.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskNum - 1);
        tasks.delete(taskNum - 1);
        ui.taskDeleted(task, tasks);
        storage.save(tasks);
    }
}
