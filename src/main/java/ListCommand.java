public class ListCommand extends Command {

    public ListCommand(String commandWord) {
        super(commandWord);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printTaskList(ui);
    }
}
