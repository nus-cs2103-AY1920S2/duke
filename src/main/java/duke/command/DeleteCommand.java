package duke.command;

import duke.task.Task;
import duke.main.TaskList;

public class DeleteCommand {

    /**
     * Deletes the task from the list of tasks created.
     * @param tasks is the list of tasks created
     * @param taskIndex the index of the task to be deleted
     * @return the output to inform user that the task has been deleted
     */
    public static String run(TaskList tasks, int taskIndex) {
        Task taskToDelete = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return "Noted, I've removed the following task:\n" + "  " + taskToDelete + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";

    }
}
