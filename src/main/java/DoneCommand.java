public class DoneCommand extends Command {

    int taskNumber;

    DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = tasks.getTaskList().get(taskNumber - 1);
        task.mark();
        ui.printDone(task, taskNumber, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
