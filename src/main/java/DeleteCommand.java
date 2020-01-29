public class DeleteCommand extends Command {
    public static boolean run(TaskList taskList, String index) {
        try {
            Ui.taskDeleted(taskList.deleteTask(index), taskList);
        } catch (InvalidArgumentException e) {
            Ui.print(e);
        }
        return true;
    }
}
