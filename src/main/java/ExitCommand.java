public class ExitCommand extends Command {

    public ExitCommand() {
    }
    public void execute(Ui ui, Storage storage, TaskList tasklist) {
        ui.printExit();
        System.exit(0);
    }
}
