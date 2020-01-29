public class TaskCommand extends Command {
    public static boolean addTask(TaskList taskList, Task t) {
        return taskList.addTask(t) >= 0;
    }
}
