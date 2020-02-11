package duke.command;

import duke.gui.Gui;
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

    public Task setDone(TaskList taskList) {
        try {
            Task task = taskList.getTaskList().get(index);
            task.done();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(Integer.toString(index + 1));
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = setDone(taskList);
        ui.showDone(task);
    }

    @Override
    public String execute(TaskList taskList, Gui gui) {
        Task task = setDone(taskList);
        return gui.showDone(task);
    }
}
