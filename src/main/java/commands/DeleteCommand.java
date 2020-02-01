package commands;

import dukeexception.DukeException;
import dukeexception.InvalidIndexException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.removeTask(this.index - 1);
            storage.saveTasks(tasks.getTasks());
            String msg = "Noted. I've removed this task:\n" + t + '\n';
            msg += "Now you have " + tasks.getNumTasks() + " tasks in the list.";
            ui.printMsg(msg);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
