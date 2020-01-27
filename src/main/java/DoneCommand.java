public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.markDone(this.index, storage);
    }
}
