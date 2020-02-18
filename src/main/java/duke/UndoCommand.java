package duke;

public class UndoCommand implements Command {
    private int undoIndex;

    public UndoCommand(int undoIndex) {
        this.undoIndex = undoIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            return tasks.undo(undoIndex);
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
