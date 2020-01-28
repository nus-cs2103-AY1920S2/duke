public class Exit extends Command {

    public Exit(Task task) {
        super(task);
    }

    public Exit() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
