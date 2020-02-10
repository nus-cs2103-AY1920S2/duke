package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskListHistory;

import java.util.Optional;

/**
 * Represents a delete action for a given task number in list.
 */
public class DeleteCommand extends Command {
    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Removes task from task list based on given task number, prints out deleted task information and
     * updates save file.
     *
     * @param tasks   list of tasks
     * @param ui      used to display information to user
     * @param storage used to access save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        // Task number given starts from 1
        try {
            TaskListHistory.update(tasks.copy());
            Task removedTask = tasks.remove(taskNumber - 1);
            ui.printTaskDeletion(removedTask, tasks.size());
            updateSaveFile(storage, ui, tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showExceptionMessage(new DukeException("Invalid task number given for deletion..."));
        }
        return Optional.of(tasks);
    }
}
