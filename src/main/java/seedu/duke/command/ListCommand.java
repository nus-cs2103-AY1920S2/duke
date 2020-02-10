package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.util.Collections;

/**
 * Represents a command to display the list of tasks.
 */
public class ListCommand extends Command {
    TaskList taskList;

    /**
     * Represents a ListCommand object.
     *
     * @param taskList The TaskList object.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints out the list of tasks.
     *
     * @param taskList The TaskList object.
     * @param ui The User Interface object.
     * @param storage The hard disk object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Collections.sort(taskList.getTasks());
        taskList.printList();
    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }
}
