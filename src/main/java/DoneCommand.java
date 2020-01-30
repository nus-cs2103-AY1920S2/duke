import java.io.IOException;

public class DoneCommand extends Command {

    private int doneIndex;

    public DoneCommand(String commandWord, int doneIndex) {
        super(commandWord);
        this.doneIndex = doneIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException{
        Task task = tasks.getTask(this.doneIndex);
        tasks.markTaskAsDone(this.doneIndex);
        ui.printDoneMessage(tasks, task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            ui.showMarkingAsDoneError(e.getMessage());
        }
    }
}
