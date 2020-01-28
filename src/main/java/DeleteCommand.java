import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String command, String description) {
        super(command, description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int num = Integer.parseInt(description);

        tasks.delete(num);

        try {
            storage.write(tasks);
        } catch(IOException e) {
            ui.showError(e);
        }
    }
}
