public class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        int oldNum = tasks.getTaskList().size();
        tasks.getTaskList().add(task);
        assert tasks.getTaskList().size() + 1 == oldNum : "Task added incorrectly";
        ui.printAdd(task, tasks);
    }

    public boolean isExit() {
        return false;
    }
}
