public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeToFile(tasks.getTasks());
        ui.showExit();
    }
}
