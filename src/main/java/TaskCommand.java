import java.io.IOException;

public class TaskCommand extends Command {
    private Task task;
    public TaskCommand(Task item) {
        task = item;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.addTask(task);
            storage.save(tasks);
            ui.showAdd(task, tasks.tasks.size());
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }
}


