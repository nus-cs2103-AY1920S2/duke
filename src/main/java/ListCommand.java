import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    boolean isExit() {
        return false;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Here are the tasks in your list:");
        List<Task> list = tasks.getList();
        ui.printList(list);
    }
}