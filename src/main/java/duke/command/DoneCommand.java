package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(taskNumber).markAsDone();
    }
}
