package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class AddCommand implements Command {
    private TaskList taskList;
    private Task task;

    public AddCommand(TaskList taskList, Task task) {
        this.task = task;
        this.taskList = taskList;
    }

    public String execute() {
        taskList.add(task);
        return "Got it. I've added this task:\n"
                + task
                + "\nNow you have " + taskList.size() + " tasks on the list.";
    }
}
