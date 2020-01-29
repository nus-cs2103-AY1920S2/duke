package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a done command. Upon execution, updates data/duke.txt and produces some feedback to the user.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * @param index Index of the done task
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task tempTask = tasks.getByIndex(index);
        tempTask.markDone();
        storage.saveAll(tasks);
        ui.showDone(tempTask);
    }
}
