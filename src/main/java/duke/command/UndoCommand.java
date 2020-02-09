package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.TaskListHistory;

import java.util.Optional;

/**
 * Represents an Undo action.
 */
public class UndoCommand extends Command {
    public UndoCommand() {
        super(false);
    }

    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        if (TaskListHistory.getStack().size() == 1) {
            // Unable to undo
            ui.showExceptionMessage(new DukeException("Nothing to undo..."));
            return Optional.empty();
        }
        // Get previous state
        Optional<TaskList> previousTaskList = TaskListHistory.getPreviousState();
        if (previousTaskList.isPresent()) {
            tasks.setTasks(previousTaskList.get().getTasks());
            storage.updateSaveFile(tasks);
            ui.printMessage("Undo Success!");
        }
        return Optional.of(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
