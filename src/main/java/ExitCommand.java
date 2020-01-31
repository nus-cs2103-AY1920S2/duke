public class ExitCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }
}
