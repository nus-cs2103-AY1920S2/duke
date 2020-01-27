package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    protected int taskNo;

    public DeleteCommand(int taskNo) {
        super(false);
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(taskNo);
    }
}
