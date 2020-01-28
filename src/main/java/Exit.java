import java.io.IOException;

public class Exit extends Command {
    Exit() {
        super();
    }

    /**
     * Executes exit command.
     *
     * @param tasks Task object.
     * @param ui UI object.
     * @param storage Storage object.
     * @throws IOException Throws IOException.
     */
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
