import java.io.IOException;

public class CommandBye extends Command {

    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {
        try {
            storage.storeFile(tasks.getTasks());
            ui.showEndMessage();
        } catch (IOException e) {
            ui.displayError(e);
        }
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
