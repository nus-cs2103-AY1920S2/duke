package duke.command;

import duke.TaskList;
import duke.gui.Gui;
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

    @Override
    public String execute(TaskList taskList, Gui gui) {
        Todo task = new Todo(description);
        taskList.addTask(task);
        return gui.showAdd(task, taskList.getTaskList());
    }
}
