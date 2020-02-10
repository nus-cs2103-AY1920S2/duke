package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskListHistory;

import java.util.Optional;

/**
 * Represents a command marking a given task number in task list as done.
 */
public class DoneCommand extends Command {
    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a given task number in task list as done, prints out task summary.
     *
     * @param tasks   list of tasks
     * @param ui      used to display information to user
     * @param storage used to update save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(taskNumber - 1);
            ui.markTaskAsDone(task);
            TaskListHistory.update(tasks.copy());
            updateSaveFile(storage, ui, tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showExceptionMessage(new DukeException("Invalid Task Number given!"));
        }
        return Optional.of(tasks);
    }
}
