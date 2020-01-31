public class ByeCommand extends Command {
    public ByeCommand(String cmd) {
        super(cmd);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        ui.sayBye();
    }
}
