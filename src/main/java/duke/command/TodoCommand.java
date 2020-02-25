package duke.command;

import duke.main.TaskList;
import duke.task.Todo;

public class TodoCommand implements Command {

    /**
     * Add the todo task to the list of tasks.
     * @param tasks is the list of tasks created
     * @param task is the todo task to be added
     * @return the output to inform user that the todo task has been added
     */
    public static String run(TaskList tasks, String task) {
        Todo todo = new Todo(task);
        tasks.add(todo);
        return "Got it, I've added the following task:\n" + "  " + todo + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

}