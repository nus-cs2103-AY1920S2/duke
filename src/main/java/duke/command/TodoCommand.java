package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a todo command. Upon execution, updates data/duke.txt and produces some feedback to the user.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Create instance of TodoCommand.
     *
     * @param description Description of the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.saveSingle(task);
        return ui.showAddTask(task, tasks.getSize());
    }
}
