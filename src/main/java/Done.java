public class Done extends Command {

    private int taskNumber;

    public Done(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(taskNumber).markAsDone();
        storage.save(tasks);
        return ui.showDoneMessage(tasks.getTask(taskNumber).getStatusIcon(), tasks.getTask(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
