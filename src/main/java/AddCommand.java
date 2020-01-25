public class AddCommand extends Command {

    private String toAdd;
    private Task.TaskType taskType;

    public AddCommand(String toAdd, Task.TaskType taskType) {
        this.toAdd = toAdd;
        this.taskType = taskType;
    }

    public void execute(TaskList taskList, Storage storage) {
        Task newTask = taskList.AddTask(toAdd, taskType);
        storage.SaveTaskToFile(newTask);
    }

    public boolean isExit() {
        return false;
    }
}
