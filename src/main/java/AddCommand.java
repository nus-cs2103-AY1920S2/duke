public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showMessage(tasklist.addTask(task, storage));
    }
}
