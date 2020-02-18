package duke.commands;

import duke.tasks.TaskList;

public class ListCommand implements Command {
    private TaskList taskList;

    /**
     * Creates a ListCommand that list all the tasks in the tasks list in an numerical ordered list.
     * @param taskList List of all the tasks saved by the user.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        return taskList.orderedToString();
    }
}
