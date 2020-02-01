package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int deletedTaskNumber;

    public DeleteCommand(int deletedTaskNumber) {
        this.deletedTaskNumber = deletedTaskNumber;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui) throws DukeException {
        Task deleted;
        try {
            deleted = taskList.deleteTask(deletedTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no such task with the number you have specified. " +
                    "Kindly check list again.");
        }
        ui.printDeleteMessage(deleted);
        return true;
    }
}