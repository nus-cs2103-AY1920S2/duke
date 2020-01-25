public abstract class Command {

    public abstract void execute(TaskList taskList, Storage storage);
    public abstract boolean isExit();
}
