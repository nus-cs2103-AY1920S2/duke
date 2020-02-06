package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * A command to mark a task as done.
 */
public class DoneCommand extends Command {
    private int idx;

    public DoneCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.markTaskAsDone(this.idx);
            storage.save(tasks.getTasks());
            String response = "Nice! I've marked this task as done:\n" + t;
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
