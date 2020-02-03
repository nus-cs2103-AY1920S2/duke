public class List extends Command {

    public List() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
