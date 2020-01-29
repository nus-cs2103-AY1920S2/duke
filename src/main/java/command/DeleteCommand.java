package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

/**
 * Represents a command where done is called which changes the boolean isCheck value. A <code>DoneCommand</code> corresponds
 * to when a task is complete with id of e.g. <code>2</code>
 */
public class DeleteCommand extends Command {
    protected int id;

    public DeleteCommand(int id) {
        this.id = id;
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
        Task task = taskList.get(id - 1);
        taskList.delete(id - 1);
        ui.displayDeletedTask(task, taskList);
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