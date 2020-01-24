public class ByeCommand extends Command{
    public ByeCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
