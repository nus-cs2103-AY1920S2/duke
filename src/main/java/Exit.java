import java.io.IOException;

public class Exit extends Command {
    Exit() {
        super();
    }

    /**
     * Executes exit command. Initially stores list items in text file.
     *
     * @param tasks Task object.
     * @param storage Storage object.
     * @return Affirmation that exit will be done.
     * @throws IOException Throws IOException.
     */
    public String execute(TaskList tasks, Storage storage) throws IOException {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }

    @Override
    boolean isExit() {
        return true;
    }
}
