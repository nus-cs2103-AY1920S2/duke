package duke;

public class ClearCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.clear();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
