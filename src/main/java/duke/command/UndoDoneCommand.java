package duke.command;

import duke.interaction.Ui;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the Command for the "done" input by the user.
 * It sets the input task index "isDone" status to true.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class UndoDoneCommand extends Command {

    int undoneIndex;

    /**
     * UndoDoneCommand constructor.
     *
     * @param index of the task in collection to be set Not Done.
     */
    public UndoDoneCommand(int index) {
        undoneIndex = index;
    }

    /**
     * Executes Done behaviour of setting given task to Done.
     *
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        taskList.undoneTask(undoneIndex);
        storage.saveTaskListToFile(taskList);
        Ui.showTaskUndone(taskList.getList().get(undoneIndex));
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
        return "done <index> - Updates the task at the given index to \"Done\" status.";
    }
}
