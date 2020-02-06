package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidIndexException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task task = taskList.getTaskList().get(index);
            taskList.getTaskList().remove(task);
            ui.showDelete(task, taskList.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(Integer.toString(index + 1));
        }
    }
}
