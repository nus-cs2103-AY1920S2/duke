package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

/**
 * Represents the command when delete is called with the id of task as argument. A <code>DeleteCommand</code>
 * object represents the command when deleting the task with id e.g. <code>5</code>
 */
public class DoneCommand extends Command {
    protected int id;

    public DoneCommand(int id) {
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
        taskList.get(id - 1).setCheck();
        ui.displayDoneTask(taskList.get(id - 1));
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