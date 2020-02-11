package command;

import common.Storage;
import task.Task;
import task.TaskList;
import exception.DukeException;
import ui.TextUi;

/**
 * Represents a command that adds a Task to TaskList.
 */
public class AddCommand extends Command {

    protected Task thisTask;

    public AddCommand(Task givenTask) {
        super();
        this.thisTask = givenTask;
    }

    /**
     * Executes the "adding" type of commands.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @throws DukeException An exception which represents errors in duke input or storage
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        tasks.add(this.thisTask);
        storage.writeToFile(tasks.getList());
        return textUi.showAddingTask_Str(thisTask, tasks);
    }

    public boolean isExit() {
        return false;
    }

}
