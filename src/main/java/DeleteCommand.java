public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(index);
        ui.showDeletedTask(deletedTask);
        storage.save(tasks.getAllTasks());
    }
}
