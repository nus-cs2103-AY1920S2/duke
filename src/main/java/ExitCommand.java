/**
 * A command to exit Duke.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String farewell = "Good day my friend! I'm here anytime you need me :)";
        ui.showMsg(farewell);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
