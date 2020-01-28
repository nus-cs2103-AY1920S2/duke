public class Add extends Command {

    public Add(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(super.getTask());
        ui.showCustomiseMessage(super.getTask().toString(), tasks.getSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
