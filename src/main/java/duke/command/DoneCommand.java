package duke.command;

import java.util.ArrayList;
import java.util.List;
import duke.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    private final int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public ExecuteResult execute(List<Task> tasks) throws DukeException {
        assert tasks != null;

        if (doneIndex < 0 || doneIndex >= tasks.size()) {
            throw new DukeException("Oops, done index is out of bounds");
        }

        Task doneTask = tasks.get(doneIndex).setDone(true);
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.set(doneIndex, doneTask);
        return new ExecuteResult(
                newTasks,
                "Nice! I've marked this task as done:\n"
                + "  " + doneTask,
                true
        );
    }
}
