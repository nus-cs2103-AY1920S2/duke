package duke.commands;

import duke.tasks.TaskList;

import java.util.List;

public class DoneCommand implements Command {
    private TaskList taskList;
    private int taskIndex;

    /**
     * Creates a DoneCommand that marks the task
     * with the given task number as done.
     * @param taskList List of all the tasks saved by the user.
     * @param details Task number to be marked as done.
     */
    public DoneCommand(TaskList taskList, List<String> details) {
        this.taskList = taskList;
        this.taskIndex = Integer.parseInt(details.get(0)) - 1;
    }

    @Override
    public String execute() {
        taskList.markAsDone(taskIndex);
        return taskList.getTask(taskIndex) + " is marked as done!";
    }
}
