public class ClearCommand extends Command {
    public static boolean run(TaskList taskList) {
        taskList.clearTasks();
        Ui.tasksCleared();
        return true;
    }
}
