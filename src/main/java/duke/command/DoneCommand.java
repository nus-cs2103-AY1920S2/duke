package command;
import java.io.IOException;

import storage.Storage;

import task.TaskList;

import ui.Ui;
/**
 * Represents the command of marking a task at the index as done. A <code>DoneCommand</code> object corresponds to the
 * command of marking the task at the index as done e.g., <code>2</code>
 */
public class DoneCommand extends Command{
    int taskNum;
    public DoneCommand(int taskNum){
        this.taskNum = taskNum;
    }
    /**
     * Marks a task as done.
     * If storage file is invalid, an error message is returned.
     * @param tasks The task list to be updated.
     * @param ui The user interface to be to be shown.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.getTask(taskNum - 1).markDone();
        ui.taskDone(tasks.getTask(taskNum - 1));
        storage.save(tasks);
    }
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
