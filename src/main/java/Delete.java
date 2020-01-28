public class Delete extends Command {

    private int taskNumber;

    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        Task deletedTask = tasks.deleteTask(taskNumber);
        ui.showDeleteMessage(deletedTask, tasks.getSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
