package duke.command;

import duke.task.Task;
import duke.main.TaskList;

public class DoneCommand {
    public static String run(TaskList tasks, int taskIndex) {
        Task taskToDo = tasks.get(taskIndex);
        taskToDo.markAsDone();
        return "Noted, I've marked the following task:\n" + "  " + taskToDo + " as done! \n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
