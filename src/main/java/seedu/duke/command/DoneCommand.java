package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.DukeIOException;
import seedu.duke.task.Task;

public class DoneCommand extends Command {

    private final int selectedTaskIndex;

    public DoneCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        Task selectedTask = tasks.get(selectedTaskIndex);
        selectedTask.markAsDone();
        storage.save(tasks);
        ui.printDoneMessage(tasks, selectedTask);
    }
}
