package command;
import java.io.IOException;
import task.*;
import ui.*;
import storage.*;

public class AddCommand extends Command{
    Task task;
    public AddCommand(Task task){
        this.task = task;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasksList, Ui ui, Storage storage) throws IOException {
        tasksList.add(task);
        ui.taskAdded(task, tasksList);
        storage.save(tasksList);
    }
}
