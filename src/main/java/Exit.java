import java.io.IOException;

public class Exit extends Command {
    Exit() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showLine();
        ui.printString("Bye. Hope to see you again soon!");
        ui.showLine();
        storage.save(tasks);
    }

    boolean isExit() {
        return true;
    }
}
