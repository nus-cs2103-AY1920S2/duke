package duke.command;

import duke.task.Task;
import duke.main.TaskList;

import java.util.ArrayList;

public class FindCommand {

    /**
     * Finds all the tasks that contains the keyword.
     * @param tasks is all the tasks that has been saved
     * @param keyword is the keyword by the user that he wants to find in tasks
     * @return the formatted output that contains all the tasks that matches keyword.
     */
    public static String run(TaskList tasks, String keyword) {
        StringBuilder output = new StringBuilder();

        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        output.append("Here are the matching tasks in your list: ");
        for (int i = 0; i < matchingTasks.size(); i++) {
            String currTask = i + 1 + "." + matchingTasks.get(i);
            output.append(System.lineSeparator());
            output.append(currTask);
        }
        return output.toString();

    }
}
