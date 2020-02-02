public class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute (Storage storage, TaskList taskList, Squirtle ui) {
        taskList.addTask(task);
        storage.update(taskList.getTaskList());
        ui.taskMsg(task, taskList.getListSize());
        return true;
    }
}
