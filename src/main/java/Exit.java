import java.io.IOException;

public class Exit extends Command {
    Exit() {
        super();
    }

    /**
     * Executes exit command.
     *
     * @param tasks Task object.
     * @param storage Storage object.
     * @throws IOException Throws IOException.
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }

    boolean isExit() {
        return true;
    }
}
