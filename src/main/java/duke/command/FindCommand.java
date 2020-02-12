package duke.command;

import duke.Storage;
import duke.common.Message;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Executes the command and display tasks that matches the search
     * string.
     */
    public String execute(TaskList tasks, Storage storage) {
        int taskCount = 0;
        String filteredTasks = "";

        for (int i = 1; i <= tasks.getLength(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(search)) {
                filteredTasks += i + "." + task + "\n";
                taskCount++;
            }
        }

        String output = Message.TASK_ADDED + "\n"
                + Message.DIVIDER + "\n"
                + filteredTasks + "\n"
                + Message.DIVIDER + "\n"
                + Message.showNumberOfTasksFound(taskCount);
        return output;
    }
}