public class DoneCommand extends Command {
    public static boolean run(TaskList taskList, String index) {
        try {
            Ui.taskMarkedAsDone(taskList.markAsDone(index));
        } catch (InvalidArgumentException e) {
            Ui.print(e);
        }
        return true;
    }
}
