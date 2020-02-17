/**
 * DoneCommand is a sub-class of Command.
 * It handles the marking of completion of tasks.
 */
public class DoneCommand extends Command {

    int taskNumber;

    DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * This method gets the task via the taskNumber then marks it as completed.
     *
     * @param tasks The task list where the task is found.
     * @param ui The UI class to print out the messages.
     * @param storage The Storage class.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task task = tasks.getTaskList().get(taskNumber - 1);
        task.mark();
        ui.printDone(task, taskNumber, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
