public class ExitCommand extends Command {

    public ExitCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }
}
