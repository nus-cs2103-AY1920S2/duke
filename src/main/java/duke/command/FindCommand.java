package duke.command;

import duke.task.Task;
import duke.main.TaskList;

import java.util.ArrayList;

public class FindCommand {

    public static String run(TaskList tasks, String keyword) {
        StringBuilder output = new StringBuilder();

        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        output.append("Here are the matching tasks in your list: ");
        for (int i = 0; i < matchingTasks.size(); i++) {
            String currTask = i+1 + "." + matchingTasks.get(i);
            output.append(System.lineSeparator());
            output.append(currTask);
        }
        return output.toString();

    }
}
