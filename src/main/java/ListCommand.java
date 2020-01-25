public class ListCommand extends Command {

    public void execute(TaskList taskList, Storage storage) {
        Ui.ShowAllTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
