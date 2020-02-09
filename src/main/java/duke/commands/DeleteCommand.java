package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand implements Command{
    private TaskList taskList;
    private int taskNumber;

    public DeleteCommand(TaskList taskList, int taskNumber) {
        this.taskList = taskList;
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute() {
        Task task = taskList.getTask(taskNumber);
        taskList.delete(taskNumber);
        return "Noted. I've removes this task:\n"
                + task
                + "\nNow you have " + taskList.size() + "tasks on the list.";
    }
}
