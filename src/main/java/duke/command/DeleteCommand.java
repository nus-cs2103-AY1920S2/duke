package duke.command;

import duke.DukeException;
import duke.TaskList;

public class DeleteCommand extends Command {
    private final int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) throws DukeException {
        if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
            return new ExecuteResult(
                    tasks.remove(deleteIndex),
                    "Noted. I've removed this task:\n"
                    + "  " + tasks.get(deleteIndex) + "\n"
                    + String.format("Now you have %d tasks in the list", tasks.size() - 1),
                    true
            );
        } else {
            throw new DukeException("Oops, delete index is out of bounds");
        }
    }
}
