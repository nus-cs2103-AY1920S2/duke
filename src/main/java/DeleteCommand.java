import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DeleteCommand extends Command {

    private final int selectedTaskIndex;

    public DeleteCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task selectedTask = tasks.get(selectedTaskIndex);
        tasks.remove(selectedTaskIndex);
        ui.printDeleteMessage(tasks, selectedTask);
        storage.save(tasks);
    }
}
