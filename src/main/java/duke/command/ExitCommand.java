package duke.command;

import java.util.List;
import duke.TaskList;

public class ExitCommand extends Command {
    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(tasks, List.of("Bye. Hope to see you again soon!"), false);
    }
}
