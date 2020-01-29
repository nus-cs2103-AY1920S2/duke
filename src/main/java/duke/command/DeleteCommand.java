package duke.command;

import duke.exception.DukeException;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    protected int indexToDelete;

    public DeleteCommand(int index) {
        this.indexToDelete = Math.max(0, index);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        DukeException.throwIf(!taskList.isIndexValid(indexToDelete), "The input index is out of bounds!");
        Task deletedTask = taskList.removeAtIndex(indexToDelete);
        ui.stylizedPrint(
                "You have removed the following task:\n",
                "\t" + deletedTask.toString(),
                String.format("You now have %d task(s) in the list.", taskList.size()));
    }
}
