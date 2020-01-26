package command;
import task.*;
import ui.*;
import storage.*;
import java.io.IOException;

public class ByeCommand extends Command {
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showByeMsg();
    }
}
