import java.io.IOException;

public class DoneCommand extends Command{
    int taskNum;
    public DoneCommand(int taskNum){
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.getTask(taskNum - 1).isDone = true;
        ui.taskDone(tasks.getTask(taskNum - 1));
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
