/**
 * A command that Duke does not understand.
 */
public class UnknownCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
