public class ListCommand extends Command {
    public ListCommand(String command, String description) {
        super(command, description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listRecord();
    }
}
