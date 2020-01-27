

public abstract class Command{
    LIST, CREATETODO, CREATEDEADLINE, CREATEEVENT, DONE, INVALID
    public String[] inputArr;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}