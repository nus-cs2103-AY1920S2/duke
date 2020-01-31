public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "I do not understand what you mean!!! :(";
        ui.printMsg(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
