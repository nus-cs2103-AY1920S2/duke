package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;

public class DoneCommand extends Command {
    private final int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) throws DukeException {
        if (doneIndex >= 0 && doneIndex < tasks.size()) {
            Task doneTask = tasks.get(doneIndex).setDone(true);
            return new ExecuteResult(
                    tasks.set(doneIndex, doneTask),
                    "Nice! I've marked this task as done:\n"
                    + "  " + doneTask,
                    true
            );
        } else {
            throw new DukeException("Oops, done index is out of bounds");
        }
    }
}
