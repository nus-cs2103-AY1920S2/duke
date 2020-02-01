package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a delete command. Upon execution, updates data/duke.txt and produces some feedback to the user.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Create instance of DeleteCommand.
     *
     * @param index Index of the delete task
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task tempTask = tasks.deleteTaskByIndex(index);
        storage.saveAll(tasks);
        return ui.showDeleteTask(tempTask, tasks.getSize());
    }
}
