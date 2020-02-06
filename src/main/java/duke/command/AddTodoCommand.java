package duke.command;

import duke.TaskList;
import duke.task.Todo;
import duke.Ui;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Todo task = new Todo(description);
        taskList.addTask(task);
        ui.showAdd(task, taskList.getTaskList());
    }
}
