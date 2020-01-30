public class CompleteTaskCommand extends Command {
    private int index;

    public CompleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task completedTask = tasks.completeTask(index);
        ui.showDonetask(completedTask);
        storage.save(tasks.getAllTasks());
    }
}
