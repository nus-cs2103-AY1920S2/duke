package duke;

public class DoneCommand implements Command {
    private int doneIndex;  //doneIndex is 0-indexed

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //doneIndex is 0-indexed
        try {
            return tasks.done(doneIndex);
        } catch (IndexOutOfBoundsException e) {
            return "Index out of bounds. Please input an index between 0 and "
                    + (tasks.getTasks().size() - 1) + ".";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
