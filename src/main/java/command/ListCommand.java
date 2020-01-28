package command;
import task.*;
import ui.*;
import storage.*;
import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}