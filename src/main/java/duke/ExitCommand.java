public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
