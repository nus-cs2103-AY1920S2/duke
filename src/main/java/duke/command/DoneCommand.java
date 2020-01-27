package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            ui.markTaskAsDone(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showExceptionMessage(new DukeException("Invalid Task Number given!"));
        }
    }
}
