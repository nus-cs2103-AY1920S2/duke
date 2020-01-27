public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.showExit();
    }
}
