public class Done extends Command {

    private int taskNumber;

    public Done(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(taskNumber).markAsDone();
        ui.showDoneMessage(tasks.getTask(taskNumber).getStatusIcon(), tasks.getTask(taskNumber));
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
