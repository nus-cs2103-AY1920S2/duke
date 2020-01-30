public class ExitCommand extends Command {

    public ExitCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
