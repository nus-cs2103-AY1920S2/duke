package duke.commands;

import duke.utilities.*;
import duke.tasks.*;
import duke.Ui;
import duke.exceptions.DukeException;

public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes task from TaskList object.
     * Updates the storage.
     * Prints Ui's delete message.
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return true to indicate that this is not an ExitCommand
     * @throws DukeException
     */
    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.deleteTask(this.taskNumber);
        storage.update(taskList.getTaskList());
        ui.deleteMsg(task);
        return true;
    }
}
