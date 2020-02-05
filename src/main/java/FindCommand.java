public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String commandWord, String keyword) {
        super(commandWord);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(ui, this.keyword);
    }
}
