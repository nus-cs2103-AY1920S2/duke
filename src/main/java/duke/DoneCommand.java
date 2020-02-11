package duke;

public class DoneCommand implements Command {
    private int doneIndex;  //doneIndex is 0-indexed

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //doneIndex is 0-indexed
        ui.printString(tasks.done(doneIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
