import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try{
            Task t = tasks.deleteTask(index);
            storage.save(tasks);
            ui.showDelete(t, tasks.tasks.size());
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }
}
