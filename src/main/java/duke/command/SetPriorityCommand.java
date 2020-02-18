package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class SetPriorityCommand extends Command {

    protected int targetTaskIndex;
    protected Task.Priority targetPriority;

    /**
     * Constructor for the Set Priority Command.
     *
     * @param targetPriority The target priority to set the task to.
     */
    public SetPriorityCommand(int index, Task.Priority targetPriority) {
        this.targetPriority = targetPriority;
        this.targetTaskIndex = index;
    }

    /**
     * Prints all tasks that are currently in the TaskList.
     *
     * @param taskList The TaskList to print tasks from.
     */
    @Override
    public void execute(TaskList taskList) throws DukeException {
        DukeException.throwIf(!taskList.isIndexValid(targetTaskIndex), "The input index is out of bounds!");
        DukeException.throwIf(targetPriority == null, "The target priority is invalid!");
        taskList.get(targetTaskIndex).setPriority(targetPriority);
        System.out.println(String.format("Priority for task %d has been set to %s.", targetTaskIndex, targetPriority));
    }
}
