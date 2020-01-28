public class ListCommand implements Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
