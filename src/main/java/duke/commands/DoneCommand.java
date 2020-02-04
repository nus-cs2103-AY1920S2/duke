package duke.commands;

import duke.utilities.*;
import duke.tasks.*;
import duke.Ui;
import duke.exceptions.DukeException;

public class DoneCommand extends Command {
    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task at index taskNumber as done.
     * Updates the storage.
     * Prints Ui's done task message.
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return true to indicate that this is not an ExitCommand
     * @throws DukeException
     */
    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.doneTask(this.taskNumber);
        storage.update(taskList.getTaskList());
        ui.doneMsg(task);
        return true;
    }
}
