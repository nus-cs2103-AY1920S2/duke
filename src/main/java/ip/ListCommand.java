package ip;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayAllTasks(tasks);
    }
}
