public class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.getTaskList().add(task);
        ui.printAdd(task, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
