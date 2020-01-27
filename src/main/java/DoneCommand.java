import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DoneCommand extends Command {

    private final int selectedTaskIndex;

    public DoneCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task selectedTask = tasks.get(selectedTaskIndex);
        selectedTask.markAsDone();
        ui.printDoneMessage(tasks, selectedTask);
        storage.save(tasks);
    }
}
