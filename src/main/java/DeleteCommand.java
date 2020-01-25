import java.io.IOException;

public class DeleteCommand extends Command {
    int taskNum;
    public DeleteCommand(int taskNum){
        this.taskNum = taskNum;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskNum - 1);
        tasks.delete(taskNum - 1);
        ui.taskDeleted(task, tasks);
        storage.save(tasks);
    }
}
