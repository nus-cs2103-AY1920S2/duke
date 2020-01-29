package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.saveSingle(task);
        ui.showAddTask(task, tasks.getSize());
    }
}
