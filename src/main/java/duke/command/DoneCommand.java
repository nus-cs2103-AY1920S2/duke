package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the Command for the "done" input by the user.
 * It sets the input task index "isDone" status to true.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class DoneCommand extends Command {

    int doneIndex;

    /**
     * DoneCommand constructor.
     * @param index of the task in collection to be set Done.
     */
    public DoneCommand(int index) {
        doneIndex = index;
    }

    /**
     * Executes Done behaviour of setting given task to Done.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        if (taskList.doneTask(doneIndex)) {
            storage.saveTaskListToFile(taskList);
        }
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
