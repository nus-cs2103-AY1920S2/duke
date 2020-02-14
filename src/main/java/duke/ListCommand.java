package duke;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString(tasks.list());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
