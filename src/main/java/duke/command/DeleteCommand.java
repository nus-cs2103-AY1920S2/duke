package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * Represents a delete command. Upon execution, updates data/duke.txt and produces some feedback to the user.
 */
public class DeleteCommand extends Command {
    private List<Integer> indexes;

    /**
     * Create instance of DeleteCommand.
     *
     * @param indexes List of indexes of the delete tasks
     */
    public DeleteCommand(List<Integer> indexes) {
        this.indexes = indexes;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tempTask = tasks.deleteTaskByIndexes(indexes);
        storage.saveAll(tasks);
        ui.showDeleteTask(tempTask, tasks.getSize());
    }
}
