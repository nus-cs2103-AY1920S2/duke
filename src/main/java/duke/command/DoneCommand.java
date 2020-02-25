package duke.command;

import duke.task.Task;
import duke.main.TaskList;

public class DoneCommand {

    /**
     * Mark the task from the list of tasks as done.
     * @param tasks is the list of tasks created
     * @param taskIndex the index of the task to be completed
     * @return output to inform user that the task has been completed
     */
    public static String run(TaskList tasks, int taskIndex) {
        Task taskToDo = tasks.get(taskIndex);
        taskToDo.markAsDone();
        return "Noted, I've marked the following task:\n" + "  " + taskToDo + " as done! \n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
