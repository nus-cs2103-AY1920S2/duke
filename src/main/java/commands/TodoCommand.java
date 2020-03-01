package commands;

import java.io.IOException;

import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class TodoCommand extends Command {
    private String taskAction;
    public TodoCommand(String taskAction) {
        super();
        this.taskAction = taskAction;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Todo todo = new Todo(taskAction);
        if (tasks.checkDuplicate(todo)) {
            return "Hey man! This todo task already exists in the list. You don't wanna duplicate!\n";
        } else {
            tasks.addTask(todo);
            storage.saveTasksIntoFile(tasks);
            return ui.acknowledgeTaskAdded(tasks, todo);
        }
    }
}
