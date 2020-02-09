package duke.commands;

import duke.tasks.TaskList;

import java.util.List;

public class DoneCommand implements Command {
    private TaskList taskList;
    private int taskIndex;

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
