package duke;

public class DeleteCommand implements Command {
    private int deleteIndex;  //deleteIndex is 0-indexed

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //deleteIndex is 0-indexed
        try {
            return tasks.delete(deleteIndex);
        } catch (IndexOutOfBoundsException e) {
            //the index the user inputs is 1-indexed
            return "Index out of bounds. Please input an index between 1 and "
                    + tasks.getTasks().size() + ".";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
