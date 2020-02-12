package duke.command;

import duke.task.Task;
import duke.main.TaskList;

public class DeleteCommand {
    public static String run(TaskList tasks, int taskIndex) {
        Task taskToDelete = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return "Noted, I've removed the following task:\n" + "  " + taskToDelete + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";

    }
}
