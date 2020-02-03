public class Exit extends Command {

    public Exit(Task task) {
        super(task);
    }

    public Exit() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
