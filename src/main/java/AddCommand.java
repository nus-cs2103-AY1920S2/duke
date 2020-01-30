public abstract class AddCommand extends Command {
    Task task;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAddedTask(task, tasks.getCount());
    }
}
