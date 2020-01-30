public class InvalidFormatCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalidFormatMessage();
    }
}
