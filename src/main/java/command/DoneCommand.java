package command;

import exception.DukeException;
import io.Ui;
import task.TaskList;

public class DoneCommand extends Command {

    protected int indexToMarkAsDone;

    public DoneCommand(int index) {
        this.indexToMarkAsDone = Math.max(0, index);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        DukeException.throwIf(!taskList.isIndexValid(indexToMarkAsDone), "The input index is out of bounds!");
        taskList.markAsDone(indexToMarkAsDone);

        // Temp for visualization. TODO: remove this.
        new ListCommand().execute(taskList, ui);
    }
}
