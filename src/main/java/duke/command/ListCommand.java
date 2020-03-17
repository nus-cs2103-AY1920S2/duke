package duke.command;

import duke.main.TaskList;


public class ListCommand implements Command {
    /**
     * Lists all the tasks that has been stored so far.
     * @param tasks the list of tasks that has been created
     * @return the formatted output of all the tasks stored
     */
    public static String run(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        if (!tasks.isEmpty()) {
            output.append("Here are the tasks in your list");
        } else {
            return "There are no tasks in your list" + "\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            String currTask = i + 1 + ". " + tasks.get(i);
            output.append(System.lineSeparator());
            output.append(currTask);
        }
        return output.toString();
    }

}



