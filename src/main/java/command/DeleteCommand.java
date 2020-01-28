package command;
import task.*;
import ui.*;
import storage.*;
import java.io.IOException;

public class DeleteCommand extends Command {
    protected int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task task = taskList.get(id - 1);
        taskList.delete(id - 1);
        ui.displayDeletedTask(task, taskList);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}