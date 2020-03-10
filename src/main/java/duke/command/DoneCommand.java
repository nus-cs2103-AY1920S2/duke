package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public class DoneCommand extends Command {

    protected int indexToMarkAsDone;

    /**
     * Constructor for the Done Command.
     *
     * @param index The index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.indexToMarkAsDone = index;
    }

    /**
     *
     * @param taskList The TaskList to select task from.
     * @throws DukeException If the index to mark is invalid.
     */
    @Override
    public void execute(TaskList taskList) throws DukeException {
        DukeException.throwIf(!taskList.isIndexValid(indexToMarkAsDone), "The input index is out of bounds!");
        taskList.markAsDone(indexToMarkAsDone);
        assert(taskList.get(indexToMarkAsDone).isDone());
        // Temp for visualization. TODO: remove this.
        new ListCommand().execute(taskList);
    }
}
