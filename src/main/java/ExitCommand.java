public class ExitCommand extends Command {
    ExitCommand() {}

    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) {
        ui.exitMsg();
        return false; // return false to exit
    }
}
