package duke.command;

import duke.interaction.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents an executable command of undoing an AddCommand
 * It deletes the task at the input task index from the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class UndoDeleteCommand extends Command {

    private int indexToRestore;
    private Task taskToRestore;

    /**
     * UndoDeleteCommand constructor.
     *
     * @param taskToRestore Task object of the task to be restored.
     * @param indexToRestore integer of the task's old index.
     */
    public UndoDeleteCommand(Task taskToRestore, int indexToRestore) {
        this.taskToRestore = taskToRestore;
        this.indexToRestore = indexToRestore;
    }

    /**
     * Executes Add behaviour of deleted task at a given index.
     *
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        String output;
        output = taskList.addTaskAt(indexToRestore, taskToRestore);
        Ui.showTaskAdded(output, taskList.getList().size());
    }

    /**
     * Inform if command is an exit command.
     *
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

}
