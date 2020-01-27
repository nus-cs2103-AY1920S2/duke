public class ListCommand extends Command {
    public ListCommand() {

    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.listTasks();
    }
}
