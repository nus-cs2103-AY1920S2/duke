public abstract class Command {

    protected String commandWord;

    public Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        return "";
    };
}
