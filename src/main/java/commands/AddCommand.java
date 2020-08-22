package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(task);
            storage.write(task);
            return ui.showTaskAddMessage(taskList.getSize(), task.toString());
        } catch (DateTimeParseException e) {
            return e.getMessage();
        }
    }
}
