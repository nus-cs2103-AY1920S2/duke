package duke.command;

import java.util.ArrayList;
import java.util.List;
import duke.task.Task;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private final Task addTask;

    /**
     * Creates an add command.
     * 
     * @param addTask New task to be added
     */
    public AddCommand(Task addTask) {
        assert addTask != null;

        this.addTask = addTask;
    }

    @Override
    public ExecuteResult execute(List<Task> tasks) {
        assert tasks != null;

        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.add(addTask);
        return new ExecuteResult(
                newTasks,
                "Got it. I've added this task:\n"
                + "  " + addTask + "\n"
                + String.format("Now you have %d tasks in the list.", tasks.size() + 1),
                true
        );
    }
}
