public class ExitCommand extends Command {
    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodBye();
    }
}
