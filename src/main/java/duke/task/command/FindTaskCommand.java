package duke.task.command;

import duke.Storage;
import duke.common.message.Message;
import duke.task.Task;
import duke.task.TaskList;

public class FindTaskCommand extends TaskCommand {

    private String search;

    public FindTaskCommand(String search) {
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