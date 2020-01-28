package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.DukeIOException;
import seedu.duke.task.Task;

public class DeleteCommand extends Command {

    private final int selectedTaskIndex;

    public DeleteCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        Task selectedTask = tasks.get(selectedTaskIndex);
        tasks.remove(selectedTaskIndex);
        storage.save(tasks);
        ui.printDeleteMessage(tasks, selectedTask);
    }
}
