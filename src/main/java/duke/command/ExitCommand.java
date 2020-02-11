package duke.command;

import duke.TaskList;

public class ExitCommand extends Command {
    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(tasks, "Bye. Hope to see you again soon!", false);
    }
}
