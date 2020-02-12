package duke;

public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // do nothing
        return null;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
