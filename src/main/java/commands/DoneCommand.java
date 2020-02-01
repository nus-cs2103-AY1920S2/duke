package commands;

import dukeexception.DukeException;
import dukeexception.InvalidIndexException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class DoneCommand extends Command {
    
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.markTaskAsDone(this.index - 1);
            storage.saveTasks(tasks.getTasks());
            String msg = "Wow you finally completed something: \n";
            msg += t;
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
