public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
    }
}
