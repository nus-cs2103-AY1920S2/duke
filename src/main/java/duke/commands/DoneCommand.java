package duke.commands;

import duke.tasks.TaskList;

public class DoneCommand implements Command {
    private TaskList taskList;
    private int taskIndex;

    public DoneCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute() {
        taskList.markAsDone(taskIndex);
        return taskList.getTask(taskIndex) + " is marked as done!";
    }
}
