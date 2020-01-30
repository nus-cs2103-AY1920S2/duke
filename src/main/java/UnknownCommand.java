/**
 * A command that Duke does not understand.
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        ui.showMsg(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
