import java.io.IOException;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.printList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
