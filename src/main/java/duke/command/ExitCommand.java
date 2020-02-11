package duke.command;

import java.util.List;
import duke.task.Task;

public class ExitCommand extends Command {
    @Override
    public ExecuteResult execute(List<Task> tasks) {
        assert tasks != null;

        return new ExecuteResult(tasks, "Bye. Hope to see you again soon!", false);
    }
}
