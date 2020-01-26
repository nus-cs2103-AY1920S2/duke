package command;
import ui.*;
import storage.*;
import java.io.IOException;
import task.*;

public class DoneCommand extends Command{
    int taskNum;
    public DoneCommand(int taskNum){
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.getTask(taskNum - 1).markDone();
        ui.taskDone(tasks.getTask(taskNum - 1));
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
