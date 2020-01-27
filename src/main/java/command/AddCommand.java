package command;
import java.io.IOException;
import task.*;
import ui.*;
import storage.*;
/**
 * Represents the command of adding a task into the task list. A <code>AddCommand</code> object corresponds to a task
 * to be added to the task list e.g., <code>Task</code>
 */
public class AddCommand extends Command{
    Task task;
    public AddCommand(Task task){
        this.task = task;
    }
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */
    public boolean isExit() {
        return false;
    }
    /**
     * Adds a task to the task list, updating the user interface and storage file.
     * If storage file is invalid, an error message is returned.
     * @param tasksList The task list to be updated.
     * @param ui The user interface to be shown.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */
    public void execute(TaskList tasksList, Ui ui, Storage storage) throws IOException {
        tasksList.add(task);
        ui.taskAdded(task, tasksList);
        storage.save(tasksList);
    }
}
