package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the Command used to complete a Task.
 */
public class DoneCommand extends Command {
    private int completedTaskNumber;

    public DoneCommand(int completedTaskNumber) {
        this.completedTaskNumber = completedTaskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        Task toComplete;
        try {
            toComplete = taskList.getTask(completedTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no such task with the number you have specified. "
                    + "Kindly check list again.");
        }
        toComplete.completeStatus();
        return ui.showDoneMessage(toComplete);
    }
}