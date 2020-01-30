public class ListCommand extends Command {

    public ListCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTaskList(ui);
    }
}
