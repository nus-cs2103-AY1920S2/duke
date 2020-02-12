package duke;

public class DeleteCommand implements Command {
    private int deleteIndex;  //deleteIndex is 0-indexed

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //deleteIndex is 0-indexed
        return tasks.delete(deleteIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
