package duke.command;

import duke.main.TaskList;
import duke.task.Todo;

public class TodoCommand implements Command {
    /**
     * Prints all the tasks in the current list
     */
    public static String run(TaskList tasks, String task) {
        Todo todo = new Todo(task);
        tasks.add(todo);
        return "Got it, I've added the following task:\n" + "  " + todo + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

}