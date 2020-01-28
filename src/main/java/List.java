/**
 *
 */
public class List extends Command {

    public List() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
