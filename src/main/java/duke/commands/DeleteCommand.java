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

    @Override
    public boolean execute (Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.deleteTask(this.taskNumber);
        storage.update(taskList.getTaskList());
        ui.deleteMsg(task);
        return true;
    }
}
