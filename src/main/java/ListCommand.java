import java.io.IOException;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        tasks.printList();
    }

    public boolean isExit() {
        return false;
    }

}