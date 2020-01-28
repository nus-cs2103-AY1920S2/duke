import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command, String description) {
        super(command, description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;

        try {
            storage.write(tasks);
        } catch(IOException e) {
            ui.showError(e);
        }

        ui.goodBye();
    }
}
