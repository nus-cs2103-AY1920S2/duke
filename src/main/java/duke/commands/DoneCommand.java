package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int index;

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
