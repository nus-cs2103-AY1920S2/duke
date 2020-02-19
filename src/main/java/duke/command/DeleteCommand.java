package duke.command;

import duke.interaction.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the Command for the "delete" input by the user.
 * It deletes the task at the input task index from the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class DeleteCommand extends Command {

    private int indexToDelete;

    public DeleteCommand() {

    }

    /**
     * DeleteCommand constructor.
     *
     * @param index of the task in collection to be deleted.
     */
    public DeleteCommand(int index) {
        indexToDelete = index;
    }

    /**
     * Executes Delete behaviour of deleted task at given index.
     *
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        Task toDelete = taskList.getTask(indexToDelete);
        String output = taskList.deleteTask(indexToDelete);

        if (output != null) {
            UndoCommand.addUndoableCommand(new UndoDeleteCommand(toDelete, indexToDelete));
            storage.saveTaskListToFile(taskList);
            Ui.showTaskDelete(output, taskList.getList().size());
        } else {
            Ui.showTaskNotFound();
        }
    }

    /**
     * Inform if command is an exit command.
     *
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "delete <index> - Deletes a task at the given index in your task list.";
    }
}
