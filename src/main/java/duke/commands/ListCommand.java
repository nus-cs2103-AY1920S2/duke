package duke.commands;

import duke.tasks.TaskList;

public class ListCommand implements Command{
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        return taskList.orderedToString();
    }
}
