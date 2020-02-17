package duke;

public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
