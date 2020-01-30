import java.io.IOException;

public class AddCommand extends Command{

    private String[] commands;

    public AddCommand(String commandWord, String[] commands) {
        super(commandWord);
        this.commands = commands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.addTask(commandWord, commands);
        ui.printAddingMessage(tasks, task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            ui.showAddingError(e.getMessage());
        }
    }
}
