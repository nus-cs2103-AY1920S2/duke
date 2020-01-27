public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(taskId);
        task.markAsDone();
        ui.showDoneTask(task, tasks);
    }
}
