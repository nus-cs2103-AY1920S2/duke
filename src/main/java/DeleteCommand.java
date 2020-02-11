import java.io.IOException;

public class DeleteCommand extends Command {

    private int deleteIndex;

    public DeleteCommand(String commandWord, int deleteIndex) {
        super(commandWord);
        this.deleteIndex = deleteIndex - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        Task task = tasks.getTask(this.deleteIndex);
        tasks.deleteTask(this.deleteIndex);
        String output = ui.printDeletingMessage(tasks, task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            output += ui.showDeletingError(e.getMessage());
        }
        return output;
    }
}
