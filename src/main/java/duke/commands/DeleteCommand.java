package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

public class DeleteCommand implements Command {
    private TaskList taskList;
    private int taskNumber;

    /**
     * Creates a DeleteCommand that deletes the task
     * with the given task number from the task list.
     * @param taskList List of all the tasks saved by the user.
     * @param details Task number to be deleted.
     */
    public DeleteCommand(TaskList taskList, List<String> details) {
        this.taskList = taskList;
        this.taskNumber = Integer.parseInt(details.get(0)) - 1;
    }

    @Override
    public String execute() {
        Task task = taskList.getTask(taskNumber);
        taskList.delete(taskNumber);
        return "Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + taskList.size() + " tasks on the list.";
    }
}
