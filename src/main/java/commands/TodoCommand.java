package commands;

import java.io.IOException;

import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class TodoCommand extends Command {
    private String taskAction;

    /**
     * Constructs a TodoCommand instance.
     *
     * @param taskAction is the task action.
     */
    public TodoCommand(String taskAction) {
        super();
        this.taskAction = taskAction;
    }

    /**
     * Executes the todo command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return added todo task response.
     * @throws IOException is the exception when dealing with file.
     */
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
