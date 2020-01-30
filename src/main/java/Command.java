public abstract class Command {
    protected String command;
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    boolean isExit() {
        if (this instanceof ExitCommand) {
            return true;
        }
            return false;
    }


    abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
