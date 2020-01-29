package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

/**
 * Represents addition of task into the task list. A <code>AddCommand</code> object corresponds to when a task is
 * added e.g. <code>Task</code>
 */

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command, adding the task to the task list and also updates UI and storage file.
     * If storage file invalid, exception thrown and error message is returned.
     * @param taskList task list to be updated
     * @param ui User interface displayed
     * @param storage Save file to be updated
     * @throws IOException If storage file not found
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(task);
        ui.displayAddedTask(task, taskList);
        storage.save(taskList);
    }

    /**
     * Returns whether it is an exit command
     * @return The result of whether this command causes an exit.
     */

    @Override
    public boolean isExit() {
        return false;
    }
}