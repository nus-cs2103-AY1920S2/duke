public abstract class Command {

    protected boolean isQuitCommand;

    public Command() {
        this.isQuitCommand = false;
    }

    protected abstract void execute(Storage storage, TaskList taskList);

    protected boolean isQuit() {
        return this.isQuitCommand;
    }

}
