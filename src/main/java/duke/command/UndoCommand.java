package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
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

    /**
     * Reverts task list to previous state.
     *
     * @param tasks   list of tasks
     * @param ui      handles user interface
     * @param storage manages save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        if (TaskListHistory.getStack().size() == 1) {
            // Unable to undo
            ui.showExceptionMessage(new DukeException("Nothing to undo..."));
            return Optional.empty();
        }
        Optional<TaskList> previousTaskList = TaskListHistory.getPreviousState();
        if (previousTaskList.isPresent()) {
            tasks.setTasks(previousTaskList.get().getTasks());
            updateSaveFile(storage, ui, tasks);
            ui.printMessage("Undo Success!");
        }
        return Optional.of(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
