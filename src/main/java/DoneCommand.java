import java.io.IOException;

public class DoneCommand extends Command{
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            Task t = tasks.markTask(index);
            storage.save(tasks);
            ui.showDone(t);
        }catch (IOException e) {
            throw new DukeException("OOPS!!! Data Save Failed");
        }
    }
}
