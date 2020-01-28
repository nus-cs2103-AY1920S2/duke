import java.io.IOException;

class DoneCommand extends Command {

    private final int selectedTaskIndex;

    public DoneCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task selectedTask = tasks.get(selectedTaskIndex);
        selectedTask.markAsDone();
        storage.save(tasks);
        ui.printDoneMessage(tasks, selectedTask);
    }
}
