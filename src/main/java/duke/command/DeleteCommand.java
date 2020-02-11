package duke.command;

import java.util.ArrayList;
import java.util.List;
import duke.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public ExecuteResult execute(List<Task> tasks) throws DukeException {
        assert tasks != null;

        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new DukeException("Oops, delete index is out of bounds");
        } 

        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.remove(deleteIndex);
        return new ExecuteResult(
                newTasks,
                "Noted. I've removed this task:\n"
                + "  " + tasks.get(deleteIndex) + "\n"
                + String.format("Now you have %d tasks in the list", tasks.size() - 1),
                true
        );
    }
}
