public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Bye see you again（ｉДｉ）";
        ui.printMsg(msg);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
