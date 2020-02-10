package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class ListCommand implements Command {
    /**
     * listCommand Method prints the list of Tasks (if not empty) running index.
     *
     * @param taskList is the list of Tasks are saved and manipulated
     */
    public static String run(TaskList taskList) {
        StringBuilder sb = new StringBuilder();

        if (taskList.size() == 0) {
            sb.append("List is empty.");
        } else {
            sb.append("Here are the task(s) in your list:\n");

            //Lambda for printing of all tasks in the list
            for (Task task : taskList.getTasks()) {
                sb.append(task).append("\n");
            }
        }

        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
