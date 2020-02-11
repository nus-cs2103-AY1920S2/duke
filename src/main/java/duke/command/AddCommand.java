package duke.command;

import duke.TaskList;
import duke.task.Task;

public class AddCommand extends Command {
    private final Task addTask;

    public AddCommand(Task addTask) {
        this.addTask = addTask;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(
                tasks.add(addTask),
                "Got it. I've added this task:\n"
                + "  " + addTask + "\n"
                + String.format("Now you have %d tasks in the list.", tasks.size() + 1),
                true
        );
    }
}
