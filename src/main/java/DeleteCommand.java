public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.deleteTask(this.index, storage);
    }
}
