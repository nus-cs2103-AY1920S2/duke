package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidIndexException;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task task = taskList.getTaskList().get(index);
            task.done();
            ui.showDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(Integer.toString(index + 1));
        }
    }
}
