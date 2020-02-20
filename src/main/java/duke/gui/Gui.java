package duke.gui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class Gui {

    /**
     * Returns the string representing bye message.
     */
    public String bye() {
        return "\tBye bye friend!";
    }

    /**
     * Returns the string representing the task list.
     *
     * @param tasks the task list
     */
    public String showList(ArrayList<Task> tasks, String str) {
        StringBuilder list = new StringBuilder("\t" + str);
        for (int i = 1; i <= tasks.size(); i++) {
            list.append("\n\t").append(i).append(". ").append(tasks.get(i - 1));
        }
        return list.toString();
    }

    /**
     * Returns the string representing the task marked as done.
     *
     * @param task task that is marked as done
     */
    public String showDone(Task task) {
        return "\tNice! I've marked this task as done:\n"
                + "\t" + task;
    }

    /**
     * Returns the string representing the task deleted along with current task list.
     *
     * @param task task that is deleted
     * @param tasks the task list
     */
    public String showDelete(Task task, ArrayList<Task> tasks) {
        return "\tNice! I've deleted this task:\n"
                + "\t" + task + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Returns the string representing the task added to list.
     *
     * @param task task that is added
     * @param tasks task list
     */
    public String showAdd(Task task, ArrayList<Task> tasks) {
        return "\tGot it. I've added this task:\n"
                + String.format("\t%s\n", task)
                + String.format("\tNow you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Returns the string representing the Exception.
     *
     * @param e Exception thrown.
     */
    public String showException(Exception e) {
        return String.format("\t%s\n", e);
    }

    /**
     * Returns the string representing the Exception.
     *
     * @param e Exception that is not explicitly caught by our catches.
     */
    public String showUnknownException(Exception e) {
        return String.format("\tI don't know this error homie, take a look:\n\t%s\n", e.toString())
                + Arrays.toString(e.getStackTrace());
    }
}
