public class DeleteCommand extends Command {

    int taskNumber;

    DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = tasks.getTaskList().get(taskNumber - 1);
        tasks.getTaskList().remove(task);
        ui.printDelete(task, taskNumber, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
