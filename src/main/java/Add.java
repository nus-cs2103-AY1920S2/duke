public class Add extends Command {

    public Add(Task task) {
        super(task);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(super.getTask());
        storage.save(tasks);
        return ui.showCustomiseMessage(super.getTask().toString(), tasks.getSize());

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
