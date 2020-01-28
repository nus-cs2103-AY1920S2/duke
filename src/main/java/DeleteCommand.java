import java.io.IOException;

class DeleteCommand extends Command {

    private final int selectedTaskIndex;

    public DeleteCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        Task selectedTask = tasks.get(selectedTaskIndex);
        tasks.remove(selectedTaskIndex);
        storage.save(tasks);
        ui.printDeleteMessage(tasks, selectedTask);
    }
}
