public abstract class Command {

    protected String commandWord;

    public Command(String commandWord) {
        this.commandWord = commandWord;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
    }
}
