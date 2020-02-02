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

    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        Task task = taskList.doneTask(this.taskNumber);
        storage.update(taskList.getTaskList());
        ui.doneMsg(task);
        return true;
    }
}
