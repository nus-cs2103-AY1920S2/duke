public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);
}
